package de.griesser.poker;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CardValueCounter {

    public static Map<ResolvedCardValue, Integer> getCountByValue(Set<Card<ResolvedCardValue>> cards) {
        Map<ResolvedCardValue, Integer> countByValue = new HashMap<>();
        for (Card<ResolvedCardValue> card : cards) {
            countByValue.merge(card.getValue(), 1, Integer::sum);
        }
        return countByValue;
    }

}
