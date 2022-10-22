package de.griesser.poker;

/**
 * Card values for internal use
 * to differentiate ACE as a ONE and ACE as an ACE
 */
enum ResolvedCardValue {
    ONE("1"), 
    TWO("2"), 
    THREE("3"), 
    FOUR("4"), 
    FIVE("5"), 
    SIX("6"), 
    SEVEN("7"), 
    EIGHT("8"), 
    NINE("9"), 
    TEN("10"), 
    JACK("J"), 
    QUEEN("Q"), 
    KING("K"), 
    ACE("A");

    private final String label;

    ResolvedCardValue(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}