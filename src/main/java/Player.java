import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Player {
    private ArrayList<Card> hands;
    private long profit;

    public Player() {
        this.profit = 0;
        this.hands = new ArrayList<>(5);
    }

    public long getProfit() {
        return profit;
    }

    public void setProfit(long profit) {
        this.profit = profit;
    }

    public void pay(int value){
        profit -= value;
    }

    public void earn(int value){
        profit += value;
    }

    public void receiveCards(Card card) {
        hands.add(card);
    }

    public void removeCards(){
        hands.clear();
    }

    public ArrayList<Card> getHands() {
        return hands;
    }

    public int getFirstDigitSum(){
        int sum = 0;
        for (Card hand : hands) {
            sum += hand.getValue();
        }
        return sum % 10 == 0 ? 10 : sum % 10;
    }

    public int bestArrangement(ArrayList<Card[]> LicensedCard){
        int highest = 0;
        for(Card[] cards : LicensedCard){
            Card card1;
            Card card2;
            if(cards[0].getValue() >= cards[1].getValue()){
                card1 = cards[1];
                card2 = cards[0];
            }else{
                card1 = cards[0];
                card2 = cards[1];
            }

            if(card1.getValue() == 1 && card2.getValue() == 10 && !card2.getRank().equals("10")){
                highest = Math.max(12 + card1.getSuitsValue(), highest);
                continue;
            }

            if(card1.getRank().equals(card2.getRank())){
                if(card1.getValue() == 1){
                    highest = Math.max(12, highest);
                } else{
                    highest = Math.max(11, highest);
                }
                continue;
            }

            int sum = findBestDigitSum(new int[]{card1.getValue(), card2.getValue()}, 0, 0);
            if(sum == 0) sum = 10;
            highest = Math.max(sum, highest);
        }
        return highest;
    }

    public ArrayList<Card[]> findLicense(){
        ArrayList<Card[]> cardsWithLicense = new ArrayList<>();
        for(int i = 0; i < hands.size(); i++){
            for(int j = i + 1; j < hands.size(); j++){
                for(int k = j + 1; k < hands.size(); k++){
                    Set<Integer> set = new HashSet<>(Arrays.asList(0,1,2,3,4));
                    int first = hands.get(i).getValue() % 10;
                    int second = hands.get(j).getValue() % 10;
                    int third = hands.get(k).getValue() % 10;

                    if((first == second && second == third) || hasLicense(new int[]{first, second, third}, 0, 0) ){
                        Arrays.asList(i,j,k).forEach(set::remove);
                        Card[] LicensedCard = new Card[set.size()];
                        int index = 0;
                        for(Integer integer : set){
                            LicensedCard[index++] = hands.get(integer);
                        }
                        cardsWithLicense.add(LicensedCard);
                    }
                }
            }
        }
        return cardsWithLicense;
    }

    public boolean hasLicense(int[] values, int sum, int idx){
        if(idx >= values.length){
            return sum % 10 == 0;
        }

        if(values[idx] == 3 || values[idx] == 6){
            return hasLicense(values, sum + 3, idx + 1) || hasLicense(values,  sum + 6, idx + 1);
        } else{
            return hasLicense(values, sum + values[idx], idx + 1);
        }
    }

    public int findBestDigitSum(int[] values, int sum, int idx){
        if(idx >= values.length){
            return sum % 10;
        }

        if(values[idx] == 3 || values[idx] == 6){
            return Math.max(findBestDigitSum(values, sum + 3, idx + 1), findBestDigitSum(values,  sum + 6, idx + 1));
        } else{
            return findBestDigitSum(values, sum + values[idx], idx + 1);
        }
    }
}
