package de.griesser.poker;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;

/**
 * Comparator used to sort card values to compare 2 hands of the same type
 * - the more often a card value is represented in the hand the better
 * - card values with equal appearence frequency are sorted by their value.
 * The resulting order is wrong for straight (flush) ace low,
 * but as 2 hands of this type have exactly the same values,
 * they will be considered equal
 * [2D, 2H, 3D, 5S, 8H] -> [3, 5, 8, 2, 2] as there is a pair of 2s
 * [2D, 2H, 3D, 3S, 8H] -> [8, 2, 2, 3, 3] as there is a pair of 3s and a pair of 2s and 3 > 2
 * [2D, 2H, 2S, 3S, 8H] -> [3, 8, 2, 2, 2] as there are three 2s 
 * [2D, 2H, 2S, 3S, 3H] -> [3, 3, 2, 2, 2] as there are three 2s and a pair of 3s
 * [2D, 2H, 2S, 2C, 8H] -> [8, 2, 2, 2, 2] as there are four 2s 
 */
public class CardValueInHandComparator implements Comparator<CardValue> {

    private Map<CardValue, Integer> countByValue;

    public CardValueInHandComparator(Set<Card> cards) {
        countByValue = CardValueCounter.getCountByValue(cards);
    }

    @Override
    public int compare(CardValue cardValue1, CardValue cardValue2) {
        if (cardValue1 == cardValue2) {
            return 0;
        }
        int res = countByValue.get(cardValue1).compareTo(countByValue.get(cardValue2));
        if (res != 0) {
            return res;
        }
        return cardValue1.compareTo(cardValue2);
    }

}
