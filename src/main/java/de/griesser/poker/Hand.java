package de.griesser.poker;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
    private final List<ResolvedCardValue> sortedCardValues;

    public Hand(Set<Card<CardValue>> cards) {
        Set<Card<ResolvedCardValue>> resolvedCards = resolveCards(cards);
        HandType type = HandType.getHandType(resolvedCards);
        // in case a single ACE is present,
        // check if treating it as a ONE would lead to a better hand
        Set<Card<ResolvedCardValue>> alternative = getAlternative(resolvedCards);
        if (alternative != null) {
            HandType alternativeType = HandType.getHandType(alternative);
            if (alternativeType.compareTo(type) > 0) {
                type = alternativeType;
                resolvedCards = alternative;
            }
        }
        this.type = type;
        this.sortedCardValues = getSortedCardValues(resolvedCards);
    }

    private static Set<Card<ResolvedCardValue>> resolveCards(Set<Card<CardValue>> cards) {
        return cards.stream()
                .map(card -> new Card<>(card.getSuit(), getResolvedCardValue(card.getValue())))
                .collect(Collectors.toSet());
    }

    // Default mapping where CardValue.ACE == ResolvedCardValue.ACE
    private static ResolvedCardValue getResolvedCardValue(CardValue value) {
        switch (value) {
            case TWO:
                return ResolvedCardValue.TWO;
            case THREE:
                return ResolvedCardValue.THREE;
            case FOUR:
                return ResolvedCardValue.FOUR;
            case FIVE:
                return ResolvedCardValue.FIVE;
            case SIX:
                return ResolvedCardValue.SIX;
            case SEVEN:
                return ResolvedCardValue.SEVEN;
            case EIGHT:
                return ResolvedCardValue.EIGHT;
            case NINE:
                return ResolvedCardValue.NINE;
            case TEN:
                return ResolvedCardValue.TEN;
            case JACK:
                return ResolvedCardValue.JACK;
            case QUEEN:
                return ResolvedCardValue.QUEEN;
            case KING:
                return ResolvedCardValue.KING;
            case ACE:
                return ResolvedCardValue.ACE;
        }
        return null;
    }

    private Set<Card<ResolvedCardValue>> getAlternative(Set<Card<ResolvedCardValue>> resolvedCards) {
        Set<Card<ResolvedCardValue>> aces = getAces(resolvedCards);
        if (aces.size() == 1) {
            return substituteAceWithOne(resolvedCards, aces.iterator().next());
        }
        return null;
    }

    private Set<Card<ResolvedCardValue>> getAces(Set<Card<ResolvedCardValue>> resolvedCards) {
        return resolvedCards.stream()
                .filter(card -> card.getValue() == ResolvedCardValue.ACE)
                .collect(Collectors.toSet());
    }

    private Set<Card<ResolvedCardValue>> substituteAceWithOne(Set<Card<ResolvedCardValue>> resolvedCards,
            Card<ResolvedCardValue> ace) {
        Set<Card<ResolvedCardValue>> alternative = new HashSet<>(resolvedCards);
        alternative.remove(ace);
        alternative.add(new Card<>(ace.getSuit(), ResolvedCardValue.ONE));
        return alternative;
    }

    // The more often a card value is represented the better
    // Card values with equal appearence frequency are sorted by their value
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
