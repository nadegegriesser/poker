package de.griesser.poker;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class Hand {

    private final HandType type;
    private final List<CardValue> sortedCardValues;

    public Hand(Set<Card> cards) {
        this.type = HandType.getHandType(cards);
        this.sortedCardValues = getSortedCardValues(cards);
    }

    private static List<CardValue> getSortedCardValues(Set<Card> cards) {
        Comparator<CardValue> comparator = new CardValueInHandComparator(cards);
        return cards.stream().map(card -> card.getValue())
                .sorted(comparator)
                .collect(Collectors.toList());
    }

}
