package de.griesser.poker;

import static de.griesser.poker.CardSuit.*;
import static de.griesser.poker.CardValue.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class HandTest {

        @Test
        public void testAceRemainsAce() {
                Hand hand = new Hand(Set.of(new Card<>(CLUB, ACE),
                                new Card<>(CLUB, KING),
                                new Card<>(SPADE, QUEEN),
                                new Card<>(CLUB, JACK),
                                new Card<>(CLUB, TEN)));

                assertEquals(HandType.STRAIGHT, hand.getType());
                assertEquals(List.of(ResolvedCardValue.TEN,
                                ResolvedCardValue.JACK,
                                ResolvedCardValue.QUEEN,
                                ResolvedCardValue.KING,
                                ResolvedCardValue.ACE), hand.getSortedCardValues());
        }

        @Test
        public void testAceTurnsToOne() {
                Hand hand = new Hand(Set.of(new Card<>(CLUB, ACE),
                                new Card<>(SPADE, TWO),
                                new Card<>(CLUB, THREE),
                                new Card<>(CLUB, FOUR),
                                new Card<>(CLUB, FIVE)));

                assertEquals(HandType.STRAIGHT, hand.getType());
                assertEquals(List.of(ResolvedCardValue.ONE,
                                ResolvedCardValue.TWO,
                                ResolvedCardValue.THREE,
                                ResolvedCardValue.FOUR,
                                ResolvedCardValue.FIVE), hand.getSortedCardValues());
        }

}
