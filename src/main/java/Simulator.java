import java.io.*;
import java.util.ArrayList;

public class Simulator extends Thread{
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        try{
            bw = new BufferedWriter(new PrintWriter("Profit.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Simulator simulator1 = new Simulator();
        Simulator simulator2 = new Simulator();
        Simulator simulator3 = new Simulator();
        Simulator simulator4 = new Simulator();
        simulator1.start();
        simulator2.start();
        simulator3.start();
        simulator4.start();
        try{
            simulator1.join();
            simulator2.join();
            simulator3.join();
            simulator4.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        bw.close();
    }

    @Override
    public void run() {
        Player dealer = new Player();
        ArrayList<Player> player = new ArrayList<>();
        ArrayList<Card> deck = new ArrayList<>(52);

        for(int i = 0; i < 3; i++){
            player.add(new Player());
        }

        for(Suits suit : Suits.values()){
            for(Ranks rank : Ranks.values()){
                deck.add(new Card(suit, rank));
            }
        }

        Game game = new Game(dealer, player, deck);
        game.shuffleDeck();

        try{
            for(int i = 0; i < 250000; i++){
                for(int j = 0; j < 50; j++){
                    game.deal(3);
                    game.firstPayout();
                    game.deal(2);
                    game.secondPayout();
                    game.newDeck();
                    game.shuffleDeck();
                }
                long[] playerEarnings = game.getEarnings();
                StringBuilder sb = new StringBuilder();
                for(long earning : playerEarnings){
                    sb.append(earning);
                    sb.append(",");
                }
                sb.replace(sb.length() - 1, sb.length(), "\n");
                bw.write(sb.toString());
                game.resetProfit();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
