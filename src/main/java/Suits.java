public enum Suits {
    SPADES(4),
    HEARTS(3),
    CLUBS(2),
    DIAMONDS(1);

    private int suitsValue;

    Suits(int suitsValue) {
        this.suitsValue = suitsValue;
    }

    public int getSuitsValue() {
        return suitsValue;
    }
}
