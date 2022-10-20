package de.griesser.poker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public enum HandType {
    STRAIGHT_FLUSH((Hand hand) -> true, (Hand hand1, Hand hand2) -> 0),
    FOUR_OF_A_KIND((Hand hand) -> true, (Hand hand1, Hand hand2) -> 0),
    FULL_HOUSE((Hand hand) -> true, (Hand hand1, Hand hand2) -> 0),
    FLUSH((Hand hand) -> true, (Hand hand1, Hand hand2) -> 0),
    STRAIGHT((Hand hand) -> true, (Hand hand1, Hand hand2) -> 0),
    THREE_OF_A_KIND((Hand hand) -> true, (Hand hand1, Hand hand2) -> 0),
    TWO_PAIRS((Hand hand) -> true, (Hand hand1, Hand hand2) -> 0),
    PAIR((Hand hand) -> true, (Hand hand1, Hand hand2) -> 0),
    HIGH_CARD((Hand hand) -> true, (Hand hand1, Hand hand2) -> 0);

    private final Predicate<Hand> predicate;
    private final Comparator<Hand> comparator;

    HandType(Predicate<Hand> predicate, Comparator<Hand> comparator) {
        this.predicate = predicate;
        this.comparator = comparator;
    }

    public int compare(Hand hand1, Hand hand2) {
        return comparator.compare(hand1, hand2);
    }

    public static HandType getHandType(Hand hand) {
        return Arrays.stream(values()).filter(type -> type.predicate.test(hand)).findFirst().get();
    }
}
