package de.griesser.poker;

import java.util.Arrays;
import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Hand {

    private final Set<Card> cards;

    @Override
    public String toString() {
        return Arrays.toString(cards.toArray());
    }
    
}
