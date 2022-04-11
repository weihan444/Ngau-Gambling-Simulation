public class Card {
    private Suits suit;
    private Ranks rank;

    public Card(Suits suit, Ranks rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getValue(){
        return rank.getValue();
    }

    public String getRank(){
        return rank.getRank();
    }

    public int getSuitsValue(){
        return suit.getSuitsValue();
    }

    @Override
    public String toString(){
        return suit.toString().charAt(0) + "" + rank.getRank();
    }
}
