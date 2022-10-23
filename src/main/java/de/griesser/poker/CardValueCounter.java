package de.griesser.poker;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CardValueCounter {

    public static Map<CardValue, Integer> getCountByValue(Set<Card> cards) {
        Map<CardValue, Integer> countByValue = new HashMap<>();
        for (Card card : cards) {
            countByValue.merge(card.getValue(), 1, Integer::sum);
        }
        return countByValue;
    }

}
