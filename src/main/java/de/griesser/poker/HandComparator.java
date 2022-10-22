package de.griesser.poker;

import java.util.Comparator;

public class HandComparator implements Comparator<Hand> {

    @Override
    public int compare(Hand hand1, Hand hand2) {
        HandType type1 = HandType.getHandType(hand1);
        HandType type2 = HandType.getHandType(hand2);
        int res = type1.compareTo(type2);
        if (res != 0) {
            return res;
        }
        return HandType.compareHandsOfSameType(hand1, hand2);
    }

}