package de.griesser.poker;

import static de.griesser.poker.CardSuit.*;
import static de.griesser.poker.CardValue.*;
import static de.griesser.poker.HandType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class HandTypeTest {

        @ParameterizedTest(name = "{index}: {0} -> {1}")
        @MethodSource("provideHandTypes")
        public void getHandType_ShouldReturnAppropriateValue(Hand hand, HandType expected) {
                assertEquals(expected, HandType.getHandType(hand));
        }

        private static Stream<Arguments> provideHandTypes() {
                return Stream.of(
                                Arguments.of(
                                                new Hand(Set.of(new Card(SPADE, TWO),
                                                                new Card(SPADE, THREE),
                                                                new Card(SPADE, FIVE),
                                                                new Card(DIAMOND, SIX),
                                                                new Card(DIAMOND, NINE))),
                                                HIGH_CARD),
                                Arguments.of(
                                                new Hand(Set.of(new Card(SPADE, KING),
                                                                new Card(SPADE, ACE),
                                                                new Card(SPADE, TWO),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR))),
                                                HIGH_CARD),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, JACK),
                                                                new Card(DIAMOND, JACK),
                                                                new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR))),
                                                PAIR),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, SEVEN),
                                                                new Card(DIAMOND, SEVEN),
                                                                new Card(CLUB, NINE),
                                                                new Card(DIAMOND, NINE),
                                                                new Card(HEART, TEN))),
                                                TWO_PAIRS),
                                Arguments.of(
                                                new Hand(Set.of(new Card(DIAMOND, TWO),
                                                                new Card(HEART, TWO),
                                                                new Card(SPADE, TWO),
                                                                new Card(CLUB, FIVE),
                                                                new Card(CLUB, SIX))),
                                                THREE_OF_A_KIND),
                                Arguments.of(
                                                new Hand(Set.of(new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(HEART, FIVE),
                                                                new Card(HEART, SIX),
                                                                new Card(HEART, SEVEN))),
                                                STRAIGHT),
                                Arguments.of(
                                                new Hand(Set.of(new Card(DIAMOND, ACE),
                                                                new Card(DIAMOND, TWO),
                                                                new Card(HEART, THREE),
                                                                new Card(HEART, FIVE),
                                                                new Card(HEART, SIX))),
                                                STRAIGHT),
                                Arguments.of(
                                                new Hand(Set.of(new Card(DIAMOND, TEN),
                                                                new Card(DIAMOND, JACK),
                                                                new Card(HEART, QUEEN),
                                                                new Card(HEART, KING),
                                                                new Card(HEART, ACE))),
                                                STRAIGHT),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(CLUB, SEVEN),
                                                                new Card(CLUB, NINE))),
                                                FLUSH),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, SIX),
                                                                new Card(DIAMOND, SIX),
                                                                new Card(HEART, SIX),
                                                                new Card(CLUB, TWO),
                                                                new Card(DIAMOND, TWO))),
                                                FULL_HOUSE),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, SEVEN),
                                                                new Card(DIAMOND, SEVEN),
                                                                new Card(HEART, SEVEN),
                                                                new Card(SPADE, SEVEN),
                                                                new Card(CLUB, EIGHT))),
                                                FOUR_OF_A_KIND),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(CLUB, FIVE),
                                                                new Card(CLUB, SIX),
                                                                new Card(CLUB, SEVEN))),
                                                STRAIGHT_FLUSH));
        }

}