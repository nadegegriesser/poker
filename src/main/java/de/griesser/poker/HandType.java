package de.griesser.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public enum HandType {
    STRAIGHT_FLUSH((Hand hand) -> hasStraight(hand) && hasFlush(hand), (Hand hand1, Hand hand2) -> 0),
    FOUR_OF_A_KIND((Hand hand) -> has4OfAKind(hand), (Hand hand1, Hand hand2) -> 0),
    FULL_HOUSE((Hand hand) -> hasFullHouse(hand), (Hand hand1, Hand hand2) -> 0),
    FLUSH((Hand hand) -> hasFlush(hand), (Hand hand1, Hand hand2) -> 0),
    STRAIGHT((Hand hand) -> hasStraight(hand), (Hand hand1, Hand hand2) -> 0),
    THREE_OF_A_KIND((Hand hand) -> has3OfAKind(hand), (Hand hand1, Hand hand2) -> 0),
    TWO_PAIRS((Hand hand) -> hasTwoPairs(hand), (Hand hand1, Hand hand2) -> 0),
    PAIR((Hand hand) -> hasPair(hand), (Hand hand1, Hand hand2) -> 0),
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

    private static boolean hasPair(Hand hand) {
        return hasCountsOfAKind(hand, Arrays.asList(2));
    }

    private static boolean has3OfAKind(Hand hand) {
        return hasCountsOfAKind(hand, Arrays.asList(3));
    }

    private static boolean has4OfAKind(Hand hand) {
        return hasCountsOfAKind(hand, Arrays.asList(4));
    }

    private static boolean hasFullHouse(Hand hand) {
        return hasCountsOfAKind(hand, Arrays.asList(3, 2));
    }

    private static boolean hasTwoPairs(Hand hand) {
        return hasCountsOfAKind(hand, Arrays.asList(2, 2));
    }

    private static boolean hasCountsOfAKind(Hand hand, List<Integer> counts) {
        Map<CardValue, Integer> countByValue = getCountByValue(hand);
        for (Integer count : counts) {
            if (!countByValue.values().remove(count)) {
                return false;
            }
        }
        return true;
    }

    private static Map<CardValue, Integer> getCountByValue(Hand hand) {
        Map<CardValue, Integer> countByValue = new HashMap<>();
        for (Card card : hand.getCards()) {
            countByValue.merge(card.getValue(), 1, Integer::sum);
        }
        return countByValue;
    }

    private static boolean hasFlush(Hand hand) {
        Map<CardSuit, Set<CardValue>> valuesBySuit = new HashMap<>();
        for (Card card : hand.getCards()) {
            valuesBySuit.computeIfAbsent(card.getSuit(), k -> new HashSet<>()).add(card.getValue());
        }
        return valuesBySuit.size() == 1;
    }

    private static boolean hasStraight(Hand hand) {
        Set<CardValue> values = hand.getCards().stream().map(card -> card.getValue()).collect(Collectors.toSet());
        if (values.size() != hand.getCards().size()) {
            return false;
        }
        List<CardValue> sortedValues = new ArrayList<>(values);
        Collections.sort(sortedValues);
        return (sortedValues.get(sortedValues.size() - 1).ordinal() - sortedValues.get(0).ordinal()) == (values.size()
                - 1);
    }
}
