package de.griesser.poker;

import static de.griesser.poker.CardValue.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public enum HandType {
    HIGH_CARD((Set<Card> cards) -> true),
    PAIR((Set<Card> cards) -> hasPair(cards)),
    TWO_PAIRS((Set<Card> cards) -> hasTwoPairs(cards)),
    THREE_OF_A_KIND((Set<Card> cards) -> has3OfAKind(cards)),
    STRAIGHT_ACE_LOW((Set<Card> cards) -> hasStraightAceLow(cards)),
    STRAIGHT((Set<Card> cards) -> hasStraight(cards)),
    FLUSH((Set<Card> cards) -> hasFlush(cards)),
    FULL_HOUSE((Set<Card> cards) -> hasFullHouse(cards)),
    FOUR_OF_A_KIND((Set<Card> cards) -> has4OfAKind(cards)),
    STRAIGHT_FLUSH_ACE_LOW((Set<Card> cards) -> hasStraightAceLow(cards) && hasFlush(cards)),
    STRAIGHT_FLUSH((Set<Card> cards) -> hasStraight(cards) && hasFlush(cards));

    private final Predicate<Set<Card>> predicate;

    HandType(Predicate<Set<Card>> predicate) {
        this.predicate = predicate;
    }

    public static HandType getHandType(Set<Card> cards) {
        return Arrays.stream(values()).sorted(Collections.reverseOrder())
                .filter(type -> type.predicate.test(cards))
                .findFirst().get();
    }

    private static boolean hasPair(Set<Card> cards) {
        return hasCountsOfAKind(cards, Arrays.asList(2));
    }

    private static boolean has3OfAKind(Set<Card> cards) {
        return hasCountsOfAKind(cards, Arrays.asList(3));
    }

    private static boolean has4OfAKind(Set<Card> cards) {
        return hasCountsOfAKind(cards, Arrays.asList(4));
    }

    private static boolean hasFullHouse(Set<Card> cards) {
        return hasCountsOfAKind(cards, Arrays.asList(3, 2));
    }

    private static boolean hasTwoPairs(Set<Card> cards) {
        return hasCountsOfAKind(cards, Arrays.asList(2, 2));
    }

    private static boolean hasCountsOfAKind(Set<Card> cards, List<Integer> counts) {
        Map<CardValue, Integer> countByValue = CardValueCounter.getCountByValue(cards);
        for (Integer count : counts) {
            if (!countByValue.values().remove(count)) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasFlush(Set<Card> cards) {
        Set<CardSuit> suits = cards.stream().map(card -> card.getSuit()).collect(Collectors.toSet());
        return suits.size() == 1;
    }

    private static boolean hasStraight(Set<Card> cards) {
        LinkedList<CardValue> sortedValues = getSortedUniqueValues(cards);
        if (sortedValues.size() != cards.size()) {
            return false;
        }
        return hasStraight(sortedValues);
    }

    private static boolean hasStraightAceLow(Set<Card> cards) {
        LinkedList<CardValue> sortedValues = getSortedUniqueValues(cards);
        if (sortedValues.size() != cards.size()) {
            return false;
        }
        if (sortedValues.removeLast() != ACE || sortedValues.getFirst() != TWO) {
            return false;
        }
        return hasStraight(sortedValues);
    }

    private static LinkedList<CardValue> getSortedUniqueValues(Set<Card> cards) {
        Set<CardValue> values = cards.stream().map(card -> card.getValue()).collect(Collectors.toSet());
        LinkedList<CardValue> sortedValues = new LinkedList<>(values);
        Collections.sort(sortedValues);
        return sortedValues;
    }

    private static boolean hasStraight(LinkedList<CardValue> sortedValues) {
        return (sortedValues.getLast().ordinal() - sortedValues.getFirst().ordinal()) == (sortedValues.size() - 1);
    }

}
