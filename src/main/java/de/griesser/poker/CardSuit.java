package de.griesser.poker;

public enum CardSuit {
    CLUB("C"), DIAMOND("D"), HEART("H"), SPADE("S");

    private final String label;

    CardSuit(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
    
}
