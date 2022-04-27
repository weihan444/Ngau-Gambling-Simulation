# Ngau-Gambling-Simulation
This project is created to simulate the Chinese Ngau Gambling card game frequently played during Chinese New Year.
The Objective of this simulation is to find out the probability distribution of this game for both players and dealer.

## Game Overview
The game the project simulate is a slightly modified version of the card game. The game is played as follow:
1. Determine the number of players in the game, in my case, I will be having a 4 person game, where 1 of them will be the dealer.
2. The game is played in rounds, each round has 2 phases, each round is played by first having each players placing their bets before the round starts.
3. After each players have placed and confirmed their bets, first phase starts by having the dealer deals out 3 cards to each players, starting from himself.
4. Then all players will sum up the points value of their cards, the ***first digit*** of the sum will determine how "big" their card and compares it to the dealer's cards.
5. The first payout will begin. If the player's card value is bigger than the dealer, they win 1x the amount of their bet, if the first digit of their sum is **0**, then it's considered a 10 and they win 2x of their bet instead. In case of a tie, there will be no winner and loser. The same thing applies if their card value is lower than the dealer's.
6. The round continues into second phase by having the dealer dealing 2 more cards to all the players, starting from himself.
7. All the players will try their best to form a best arrangement of 3-2 cards. 3 cards will act as the "license" for the other 2 cards while the 2 cards will determine how "big" their hands will be.
8. In order to have a "license", 3 of the cards needs to sum up to a multiples of 10, or having 3 of a kind. Eg. 2 8 10, J Q 10, 7 7 7
  - In this phase, the cards 3 and 6 are interchangeable, which means 3 can act as 6 and 6 can act as 3, so 3 3 8 count as having a license since both of the 3 can act as 6
9. For 2 of the remaining cards, it works the same way as the first phase of the round, sum both of the cards up and uses the first digit to determine how big the hands will be with a few special arrangement.
10. The second payout will be done according to the table below:

| Hands                 | Example|Payout|
| --------------------- |---|:----:|
| Ngau 冬菇(A♠️ + J/Q/K) | A♠️ J♠️ | 5x |
| 二 Ngau(A♥️ + J/Q/K)  | A♥️ Q♦️ | 4x |
| 三 Ngau(A♣️ + J/Q/K)  | A♣️ J♠️ | 3x |
| 小 Ngau(A♦️ + J/Q/K)  | A♦️ Q♣️ | 2x |
| 孖 Ace (Ace Pair)             | A♠️ A♣️ | 4x |
| 孖 (Pair)             | 3♦️ 3♣️ | 3x |
| Ngau 10 (Multiples of 10) | 3♦️ 4♣️/J♣️ 10♥️| 2x |
| Ngau 1-9                  | 3♦️ 5♣️/A♣️ 10♥️| 1x |
| No Ngau (No License) | No license cards| 0x |
- The hands are arranged in descending order (from biggest hands to smallest hands with the bigger one beating out the smaller one)

## Prerequisite
- Java 17
- Python with numpy and matplotlib

## Running the program
Compile the java class by doing:
```
javac -d ./target/classes/ ./src/main/java/*.java
```
Run the Simulator.class:
```
java -cp ./target/classes Simulator
```
This will create a Profit.txt file in the directory.
Then you can use program like matlab or python with numpy and matplotlib to plot the graph.

For python, I used anaconda to set up a python environment with numpy and matplotlib then run:
```
python histogram.py
python normal_distribution.py
```
