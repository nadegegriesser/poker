package de.griesser.poker;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Card {
    
    private final CardSuit suit;
    private final CardValue value;

    @Override
    public String toString() {
        return value.toString() + suit.toString();
    }

}
