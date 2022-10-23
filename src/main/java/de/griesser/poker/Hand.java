package de.griesser.poker;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;

/**
 * Compute and stores relevant information for the comparator
 * - hand type
 * - sorted card values (used for comparing when hand types are equal)
 */
@Getter
public class Hand {

    private final HandType type;
    private final List<CardValue> sortedCardValues;

    public Hand(Set<Card> cards) {
        this.type = HandType.getHandType(cards);
        this.sortedCardValues = getSortedCardValues(cards);
    }

    private static List<CardValue> getSortedCardValues(Set<Card> cards) {
        Comparator<CardValue> comparator = new CardValueComparator(cards);
        return cards.stream().map(card -> card.getValue())
                .sorted(comparator)
                .collect(Collectors.toList());
    }

}
