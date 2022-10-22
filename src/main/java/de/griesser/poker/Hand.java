package de.griesser.poker;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class Hand {

    private final HandType type;
    private final List<ResolvedCardValue> sortedCardValues;

    public Hand(Set<Card<CardValue>> cards) {
        Set<Card<ResolvedCardValue>> resolvedCards = cards.stream()
                .map(card -> new Card<>(card.getSuit(), card.getValue().getResolvedValue()))
                .collect(Collectors.toSet());
        HandType type = HandType.getHandType(resolvedCards);
        Set<Card<ResolvedCardValue>> aces = resolvedCards.stream()
                .filter(card -> card.getValue() == ResolvedCardValue.ACE)
                .collect(Collectors.toSet());
        if (aces.size() == 1) {
            Card<ResolvedCardValue> ace = aces.iterator().next();
            Set<Card<ResolvedCardValue>> alternative = new HashSet<>(resolvedCards);
            alternative.remove(ace);
            alternative.add(new Card<>(ace.getSuit(), ResolvedCardValue.ONE));
            HandType alternativeType = HandType.getHandType(alternative);
            if (alternativeType.compareTo(type) > 0) {
                type = alternativeType;
                resolvedCards = alternative;
            }
        }
        this.type = type;
        this.sortedCardValues = getSortedCardValues(resolvedCards);
    }

    private static List<ResolvedCardValue> getSortedCardValues(Set<Card<ResolvedCardValue>> cards) {
        Map<ResolvedCardValue, Integer> countByValue = CardValueCounter.getCountByValue(cards);
        return cards.stream().map(card -> card.getValue())
                .sorted((cardValue1, cardValue2) -> {
                    int res = countByValue.get(cardValue1).compareTo(countByValue.get(cardValue2));
                    if (res != 0) {
                        return res;
                    }
                    return cardValue1.compareTo(cardValue2);
                })
                .collect(Collectors.toList());
    }

}
