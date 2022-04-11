import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private Player dealer;
    private ArrayList<Player> players;
    private ArrayList<Card> refDeck;
    private ArrayList<Card> deck;

    public Game(Player dealer, ArrayList<Player> players, ArrayList<Card> refDeck) {
        this.dealer = dealer;
        this.players = players;
        this.refDeck = refDeck;
        this.deck = new ArrayList<>(refDeck);
    }

    public Player getDealer() {
        return dealer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setDealer(Player dealer) {
        this.dealer = dealer;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public void newDeck(){
        dealer.removeCards();
        for(Player player : players){
            player.removeCards();
        }
        deck = new ArrayList<>(refDeck);
    }

    public void deal(int num){
        for(int i = 0; i < num; i++){
            Card card = deck.get(deck.size() - 1);
            deck.remove(deck.size() - 1);
            dealer.receiveCards(card);
            for(Player player : players){
                card = deck.get(deck.size() - 1);
                deck.remove(deck.size() - 1);
                player.receiveCards(card);
            }
        }
    }

    public void displayFirstPhase(){
        System.out.print("Dealer:");
        for(int i = 0; i < dealer.getHands().size(); i++){
            System.out.print(" " + dealer.getHands().get(i));
        }
        System.out.print(" " + dealer.getFirstDigitSum());
        System.out.println();
        for(int i = 0; i < players.size(); i++ ){
            System.out.print("Player " + (i + 1) + ":");
            for(int j = 0; j < players.get(i).getHands().size(); j++){
                System.out.print(" " + players.get(i).getHands().get(j));
            }
            System.out.print(" " + players.get(i).getFirstDigitSum());
            System.out.println();
        }
        System.out.println();
    }

    public void displaySecondPhase(){
        System.out.print("Dealer:");
        for(int i = 0; i < dealer.getHands().size(); i++){
            System.out.print(" " + dealer.getHands().get(i));
        }
        System.out.print(" " + dealer.bestArrangement(dealer.findLicense()));
        System.out.println();
        for(int i = 0; i < players.size(); i++ ){
            System.out.print("Player " + (i + 1) + ":");
            for(int j = 0; j < players.get(i).getHands().size(); j++){
                System.out.print(" " + players.get(i).getHands().get(j));
            }
            System.out.print(" " + players.get(i).bestArrangement(players.get(i).findLicense()));
            System.out.println();
        }
        System.out.println();
    }

    public void firstPayout(){
        int dealerPoints = dealer.getFirstDigitSum();
        for(Player player : players){
            int playerPoints = player.getFirstDigitSum();
            if(dealerPoints > playerPoints){
                dealer.earn(pointsConversion(dealerPoints));
                player.pay(pointsConversion(dealerPoints));
            } else if(dealerPoints < playerPoints){
                dealer.pay(pointsConversion(playerPoints));
                player.earn(pointsConversion(playerPoints));
            }
        }
    }

    public void secondPayout(){
        int dealerPoints = dealer.bestArrangement(dealer.findLicense());
        for(Player player : players){
            int playerPoints = player.bestArrangement(player.findLicense());
            if(dealerPoints > playerPoints){
                dealer.earn(pointsConversion(dealerPoints));
                player.pay(pointsConversion(dealerPoints));
            } else if(dealerPoints < playerPoints){
                dealer.pay(pointsConversion(playerPoints));
                player.earn(pointsConversion(playerPoints));
            }
        }
    }

    public int pointsConversion(int points){
        if(points >= 1 && points <= 9) return 1;
        if(points == 10) return 2;
        if(points == 11) return 3;
        if(points == 12) return 4;
        if(points == 13) return 2;
        if(points == 14) return 3;
        if(points == 15) return 4;
        if(points == 16) return 5;
        return 0;
    }

    public void displayEarnings(){
        System.out.println("Profit of each player");
        System.out.println("Dealer: " + dealer.getProfit());
        for(int i = 0; i < players.size(); i++ ){
            System.out.println("Player " + (i + 1) + ": " + players.get(i).getProfit());
        }
    }

    public long[] getEarnings(){
        long[] profit = new long[players.size() + 1];
        profit[0] = dealer.getProfit();
        for(int i = 1; i < players.size() + 1; i++){
            profit[i] = players.get(i - 1).getProfit();
        }
        return profit;
    }

    public void resetProfit(){
        dealer.setProfit(0);
        for(Player player : players){
            player.setProfit(0);
        }
    }
}
