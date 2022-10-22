package de.griesser.poker;

public enum CardValue {
    TWO("2", ResolvedCardValue.TWO), 
    THREE("3", ResolvedCardValue.THREE), 
    FOUR("4", ResolvedCardValue.FOUR), 
    FIVE("5", ResolvedCardValue.FIVE), 
    SIX("6", ResolvedCardValue.SIX), 
    SEVEN("7", ResolvedCardValue.SEVEN), 
    EIGHT("8", ResolvedCardValue.EIGHT), 
    NINE("9", ResolvedCardValue.NINE), 
    TEN("10", ResolvedCardValue.TEN), 
    JACK("J", ResolvedCardValue.JACK), 
    QUEEN("Q", ResolvedCardValue.QUEEN), 
    KING("K", ResolvedCardValue.KING), 
    ACE("A", ResolvedCardValue.ACE);

    private final String label;
    private final ResolvedCardValue resolvedValue;

    CardValue(String label, ResolvedCardValue resolvedValue) {
        this.label = label;
        this.resolvedValue = resolvedValue;
    }

    public ResolvedCardValue getResolvedValue() {
        return resolvedValue;
    }

    public String toString() {
        return label;
    }
}