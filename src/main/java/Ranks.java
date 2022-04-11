public enum Ranks {
    KING(10, "K"),
    QUEEN(10, "Q"),
    JACK(10, "J"),
    TEN(10, "10"),
    NINE(9, "9"),
    EIGHT(8, "8"),
    SEVEN(7, "7"),
    SIX(6, "6"),
    FIVE(5, "5"),
    FOUR(4, "4"),
    THREE(3, "3"),
    TWO(2, "2"),
    ACE(1, "1");

    private int value;
    private String rank;

    Ranks(int value, String rank) {
        this.value = value;
        this.rank = rank;
    }

    public int getValue(){
        return value;
    }

    public String getRank(){
        return rank;
    }
}
