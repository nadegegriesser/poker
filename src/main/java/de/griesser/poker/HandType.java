package de.griesser.poker;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public enum HandType {
    HIGH_CARD((Set<Card<ResolvedCardValue>> cards) -> true),
    PAIR((Set<Card<ResolvedCardValue>> cards) -> hasPair(cards)),
    TWO_PAIRS((Set<Card<ResolvedCardValue>> cards) -> hasTwoPairs(cards)),
    THREE_OF_A_KIND((Set<Card<ResolvedCardValue>> cards) -> has3OfAKind(cards)),
    STRAIGHT((Set<Card<ResolvedCardValue>> cards) -> hasStraight(cards)),
    FLUSH((Set<Card<ResolvedCardValue>> cards) -> hasFlush(cards)),
    FULL_HOUSE((Set<Card<ResolvedCardValue>> cards) -> hasFullHouse(cards)),
    FOUR_OF_A_KIND((Set<Card<ResolvedCardValue>> cards) -> has4OfAKind(cards)),
    STRAIGHT_FLUSH((Set<Card<ResolvedCardValue>> cards) -> hasStraight(cards) && hasFlush(cards));

    private final Predicate<Set<Card<ResolvedCardValue>>> predicate;

    HandType(Predicate<Set<Card<ResolvedCardValue>>> predicate) {
        this.predicate = predicate;
    }

    public static HandType getHandType(Set<Card<ResolvedCardValue>> cards) {
        return Arrays.stream(values()).sorted(Collections.reverseOrder())
                .filter(type -> type.predicate.test(cards))
                .findFirst().get();
    }

    private static boolean hasPair(Set<Card<ResolvedCardValue>> cards) {
        return hasCountsOfAKind(cards, Arrays.asList(2));
    }

    private static boolean has3OfAKind(Set<Card<ResolvedCardValue>> cards) {
        return hasCountsOfAKind(cards, Arrays.asList(3));
    }

    private static boolean has4OfAKind(Set<Card<ResolvedCardValue>> cards) {
        return hasCountsOfAKind(cards, Arrays.asList(4));
    }

    private static boolean hasFullHouse(Set<Card<ResolvedCardValue>> cards) {
        return hasCountsOfAKind(cards, Arrays.asList(3, 2));
    }

    private static boolean hasTwoPairs(Set<Card<ResolvedCardValue>> cards) {
        return hasCountsOfAKind(cards, Arrays.asList(2, 2));
    }

    private static boolean hasCountsOfAKind(Set<Card<ResolvedCardValue>> cards, List<Integer> counts) {
        Map<ResolvedCardValue, Integer> countByValue = CardValueCounter.getCountByValue(cards);
        for (Integer count : counts) {
            if (!countByValue.values().remove(count)) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasFlush(Set<Card<ResolvedCardValue>> cards) {
        Set<CardSuit> suits = cards.stream().map(card -> card.getSuit()).collect(Collectors.toSet());
        return suits.size() == 1;
    }

    private static boolean hasStraight(Set<Card<ResolvedCardValue>> cards) {
        int size = cards.size();
        Set<ResolvedCardValue> values = cards.stream().map(card -> card.getValue()).collect(Collectors.toSet());
        if (values.size() != size) {
            return false;
        }
        LinkedList<ResolvedCardValue> sortedValues = new LinkedList<>(values);
        Collections.sort(sortedValues);
        return ((sortedValues.getLast().ordinal() - sortedValues.getFirst().ordinal()) == (sortedValues.size() - 1));
    }
    
}
