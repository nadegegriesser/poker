package de.griesser.poker;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public enum HandType {
    HIGH_CARD((Hand hand) -> true),
    PAIR((Hand hand) -> hasPair(hand)),
    TWO_PAIRS((Hand hand) -> hasTwoPairs(hand)),
    THREE_OF_A_KIND((Hand hand) -> has3OfAKind(hand)),
    STRAIGHT((Hand hand) -> hasStraight(hand)),
    FLUSH((Hand hand) -> hasFlush(hand)),
    FULL_HOUSE((Hand hand) -> hasFullHouse(hand)),
    FOUR_OF_A_KIND((Hand hand) -> has4OfAKind(hand)),
    STRAIGHT_FLUSH((Hand hand) -> hasStraight(hand) && hasFlush(hand));

    private final Predicate<Hand> predicate;

    HandType(Predicate<Hand> predicate) {
        this.predicate = predicate;
    }

    public static HandType getHandType(Hand hand) {
        return Arrays.stream(values()).sorted(Collections.reverseOrder())
                .filter(type -> type.predicate.test(hand))
                .findFirst().get();
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
        Set<CardSuit> suits = hand.getCards().stream().map(card -> card.getSuit()).collect(Collectors.toSet());
        return suits.size() == 1;
    }

    private static boolean hasStraight(Hand hand) {
        int size = hand.getCards().size();
        Set<CardValue> values = hand.getCards().stream().map(card -> card.getValue()).collect(Collectors.toSet());
        if (values.size() != size) {
            return false;
        }
        LinkedList<CardValue> sortedValues = new LinkedList<>(values);
        Collections.sort(sortedValues);
        if (hasStraight(sortedValues)) {
            return true;
        }
        if (sortedValues.getLast() == CardValue.ACE && sortedValues.getFirst() == CardValue.TWO) {
            sortedValues.removeLast();
            return hasStraight(sortedValues);
        }
        return false;
    }

    private static boolean hasStraight(LinkedList<CardValue> sortedValues) {
        if (sortedValues.isEmpty()) {
            return false;
        }
        return ((sortedValues.getLast().ordinal() - sortedValues.getFirst().ordinal()) == (sortedValues.size() - 1));
    }

    public static int compareHandsOfSameType(Hand hand1, Hand hand2) {
        List<CardValue> sortedCardValues1 = getSortedCardValues(hand1);
        List<CardValue> sortedCardValues2 = getSortedCardValues(hand2);
        return compareSortedCardValues(sortedCardValues1, sortedCardValues2);
    }

    public static List<CardValue> getSortedCardValues(Hand hand) {
        Map<CardValue, Integer> countByValue = getCountByValue(hand);
        return hand.getCards().stream().map(card -> card.getValue())
                .sorted((cardValue1, cardValue2) -> {
                    int res = countByValue.get(cardValue1).compareTo(countByValue.get(cardValue2));
                    if (res != 0) {
                        return res;
                    }
                    return cardValue1.compareTo(cardValue2);
                })
                .collect(Collectors.toList());
    }

    private static int compareSortedCardValues(List<CardValue> sortedCardValues1, List<CardValue> sortedCardValues2) {
        for (int i = sortedCardValues1.size() - 1; i >= 0; i--) {
            int res = sortedCardValues1.get(i).compareTo(sortedCardValues2.get(i));
            if (res != 0) {
                return res;
            }
        }
        return 0;
    }
}
