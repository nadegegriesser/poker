package de.griesser.poker;

import java.util.Comparator;
import java.util.List;

public class HandComparator implements Comparator<Hand> {

    @Override
    public int compare(Hand hand1, Hand hand2) {
        int res = hand1.getType().compareTo(hand2.getType());
        if (res != 0) {
            return res;
        }
        return compareSortedCardValues(hand1.getSortedCardValues(), hand2.getSortedCardValues());
    }

    /**
     * Compare the card values of the two hands starting by the most important one
     */
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