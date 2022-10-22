package de.griesser.poker;

import static de.griesser.poker.CardSuit.*;
import static de.griesser.poker.CardValue.*;
import static de.griesser.poker.HandType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class HandTypeTest {

        @ParameterizedTest(name = "{index}: {0} -> {1}")
        @MethodSource("provideHandAndExpectedType")
        public void getHandType_ShouldReturnAppropriateValue(Hand hand, HandType expected) {
                assertEquals(expected, HandType.getHandType(hand));
        }

        private static Stream<Arguments> provideHandAndExpectedType() {
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
                                                                new Card(HEART, FOUR),
                                                                new Card(HEART, FIVE))),
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

        @ParameterizedTest(name = "{index}: {0} -> {1}")
        @MethodSource("provideHandAndExpectedSortedCardValues")
        public void getSortedCardValues_ShouldReturnAppropriateValue(Hand hand, List<CardValue> expected) {
                assertIterableEquals(expected, HandType.getSortedCardValues(hand));
        }

        private static Stream<Arguments> provideHandAndExpectedSortedCardValues() {
                return Stream.of(
                                Arguments.of(
                                                new Hand(Set.of(new Card(SPADE, TWO),
                                                                new Card(SPADE, THREE),
                                                                new Card(SPADE, FIVE),
                                                                new Card(DIAMOND, SIX),
                                                                new Card(DIAMOND, NINE))),
                                                Arrays.asList(TWO, THREE, FIVE, SIX, NINE)),
                                Arguments.of(
                                                new Hand(Set.of(new Card(SPADE, KING),
                                                                new Card(SPADE, ACE),
                                                                new Card(SPADE, TWO),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR))),
                                                Arrays.asList(TWO, THREE, FOUR, KING, ACE)),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, JACK),
                                                                new Card(DIAMOND, JACK),
                                                                new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR))),
                                                Arrays.asList(TWO, THREE, FOUR, JACK, JACK)),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, SEVEN),
                                                                new Card(DIAMOND, SEVEN),
                                                                new Card(CLUB, NINE),
                                                                new Card(DIAMOND, NINE),
                                                                new Card(HEART, TEN))),
                                                Arrays.asList(TEN, SEVEN, SEVEN, NINE, NINE)),
                                Arguments.of(
                                                new Hand(Set.of(new Card(DIAMOND, TWO),
                                                                new Card(HEART, TWO),
                                                                new Card(SPADE, TWO),
                                                                new Card(CLUB, FIVE),
                                                                new Card(CLUB, SIX))),
                                                Arrays.asList(FIVE, SIX, TWO, TWO, TWO)),
                                Arguments.of(
                                                new Hand(Set.of(new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(HEART, FIVE),
                                                                new Card(HEART, SIX),
                                                                new Card(HEART, SEVEN))),
                                                Arrays.asList(THREE, FOUR, FIVE, SIX, SEVEN)),
                                // TODO
                                Arguments.of(
                                                new Hand(Set.of(new Card(DIAMOND, ACE),
                                                                new Card(DIAMOND, TWO),
                                                                new Card(HEART, THREE),
                                                                new Card(HEART, FOUR),
                                                                new Card(HEART, FIVE))),
                                                Arrays.asList(ACE, TWO, THREE, FOUR, FIVE)),
                                Arguments.of(
                                                new Hand(Set.of(new Card(DIAMOND, TEN),
                                                                new Card(DIAMOND, JACK),
                                                                new Card(HEART, QUEEN),
                                                                new Card(HEART, KING),
                                                                new Card(HEART, ACE))),
                                                Arrays.asList(TEN, JACK, QUEEN, KING, ACE)),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(CLUB, SEVEN),
                                                                new Card(CLUB, NINE))),
                                                Arrays.asList(TWO, THREE, FOUR, SEVEN, NINE)),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, SIX),
                                                                new Card(DIAMOND, SIX),
                                                                new Card(HEART, SIX),
                                                                new Card(CLUB, TWO),
                                                                new Card(DIAMOND, TWO))),
                                                Arrays.asList(TWO, TWO, SIX, SIX, SIX)),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, SEVEN),
                                                                new Card(DIAMOND, SEVEN),
                                                                new Card(HEART, SEVEN),
                                                                new Card(SPADE, SEVEN),
                                                                new Card(CLUB, EIGHT))),
                                                Arrays.asList(EIGHT, SEVEN, SEVEN, SEVEN, SEVEN)),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(CLUB, FIVE),
                                                                new Card(CLUB, SIX),
                                                                new Card(CLUB, SEVEN))),
                                                Arrays.asList(THREE, FOUR, FIVE, SIX, SEVEN)));
        }

        @ParameterizedTest(name = "{index}: {0} -> {1}")
        @MethodSource("provideFirstHandWins")
        public void compareHandsOfSameType_ShouldReturnNegativeValue(Hand hand1, Hand hand2) {
                assertTrue(HandType.compareHandsOfSameType(hand1, hand2) > 0);
        }

        @ParameterizedTest(name = "{index}: {0} vs {1}")
        @MethodSource("provideEqualsHands")
        public void compareHandsOfSameType_ShouldReturn0(Hand hand1, Hand hand2) {
                assertTrue(HandType.compareHandsOfSameType(hand1, hand2) == 0,
                                String.format("%s against %s should be a draw", hand1, hand2));
        }

        @ParameterizedTest(name = "{index}: {0} vs {1}")
        @MethodSource("provideFirstHandWins")
        public void compareHandsOfSameType_ShouldReturnPositiveValue(Hand hand1, Hand hand2) {
                assertTrue(HandType.compareHandsOfSameType(hand1, hand2) > 0,
                                String.format("%s should win agains %s", hand1, hand2));
        }

        private static Stream<Arguments> provideEqualsHands() {
                return Stream.of(
                                Arguments.of(
                                                new Hand(Set.of(new Card(SPADE, TWO),
                                                                new Card(SPADE, THREE),
                                                                new Card(SPADE, FIVE),
                                                                new Card(DIAMOND, SIX),
                                                                new Card(DIAMOND, NINE))),
                                                new Hand(Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FIVE),
                                                                new Card(HEART, SIX),
                                                                new Card(HEART, NINE)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(SPADE, KING),
                                                                new Card(SPADE, ACE),
                                                                new Card(SPADE, TWO),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR))),
                                                new Hand(Set.of(new Card(CLUB, KING),
                                                                new Card(CLUB, ACE),
                                                                new Card(CLUB, TWO),
                                                                new Card(HEART, THREE),
                                                                new Card(HEART, FOUR)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, JACK),
                                                                new Card(DIAMOND, JACK),
                                                                new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR))),
                                                new Hand(Set.of(new Card(SPADE, JACK),
                                                                new Card(HEART, JACK),
                                                                new Card(SPADE, TWO),
                                                                new Card(SPADE, THREE),
                                                                new Card(SPADE, FOUR)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, SEVEN),
                                                                new Card(DIAMOND, SEVEN),
                                                                new Card(CLUB, NINE),
                                                                new Card(DIAMOND, NINE),
                                                                new Card(HEART, TEN))),
                                                new Hand(Set.of(new Card(SPADE, SEVEN),
                                                                new Card(HEART, SEVEN),
                                                                new Card(SPADE, NINE),
                                                                new Card(HEART, NINE),
                                                                new Card(DIAMOND, TEN)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(HEART, FIVE),
                                                                new Card(HEART, SIX),
                                                                new Card(HEART, SEVEN))),
                                                new Hand(Set.of(new Card(HEART, THREE),
                                                                new Card(HEART, FOUR),
                                                                new Card(DIAMOND, FIVE),
                                                                new Card(DIAMOND, SIX),
                                                                new Card(DIAMOND, SEVEN)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(DIAMOND, ACE),
                                                                new Card(DIAMOND, TWO),
                                                                new Card(HEART, THREE),
                                                                new Card(HEART, FOUR),
                                                                new Card(HEART, FIVE))),
                                                new Hand(Set.of(new Card(HEART, ACE),
                                                                new Card(HEART, TWO),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(DIAMOND, FIVE)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(DIAMOND, TEN),
                                                                new Card(DIAMOND, JACK),
                                                                new Card(HEART, QUEEN),
                                                                new Card(HEART, KING),
                                                                new Card(HEART, ACE))),
                                                new Hand(Set.of(new Card(HEART, TEN),
                                                                new Card(HEART, JACK),
                                                                new Card(DIAMOND, QUEEN),
                                                                new Card(DIAMOND, KING),
                                                                new Card(DIAMOND, ACE)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(CLUB, SEVEN),
                                                                new Card(CLUB, NINE))),
                                                new Hand(Set.of(new Card(SPADE, TWO),
                                                                new Card(SPADE, THREE),
                                                                new Card(SPADE, FOUR),
                                                                new Card(SPADE, SEVEN),
                                                                new Card(SPADE, NINE)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(CLUB, FIVE),
                                                                new Card(CLUB, SIX),
                                                                new Card(CLUB, SEVEN))),
                                                new Hand(Set.of(new Card(SPADE, THREE),
                                                                new Card(SPADE, FOUR),
                                                                new Card(SPADE, FIVE),
                                                                new Card(SPADE, SIX),
                                                                new Card(SPADE, SEVEN)))));
        }

        private static Stream<Arguments> provideFirstHandWins() {
                return Stream.of(
                                Arguments.of(
                                                new Hand(Set.of(new Card(SPADE, TWO),
                                                                new Card(SPADE, THREE),
                                                                new Card(SPADE, FIVE),
                                                                new Card(DIAMOND, SIX),
                                                                new Card(DIAMOND, TEN))),
                                                new Hand(Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FIVE),
                                                                new Card(HEART, SIX),
                                                                new Card(HEART, NINE)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(SPADE, KING),
                                                                new Card(SPADE, ACE),
                                                                new Card(SPADE, TWO),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR))),
                                                new Hand(Set.of(new Card(CLUB, KING),
                                                                new Card(CLUB, QUEEN),
                                                                new Card(CLUB, TWO),
                                                                new Card(HEART, THREE),
                                                                new Card(HEART, FOUR)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, QUEEN),
                                                                new Card(DIAMOND, QUEEN),
                                                                new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR))),
                                                new Hand(Set.of(new Card(SPADE, JACK),
                                                                new Card(HEART, JACK),
                                                                new Card(SPADE, TWO),
                                                                new Card(SPADE, THREE),
                                                                new Card(SPADE, FOUR)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, SEVEN),
                                                                new Card(DIAMOND, SEVEN),
                                                                new Card(CLUB, JACK),
                                                                new Card(DIAMOND, JACK),
                                                                new Card(HEART, TEN))),
                                                new Hand(Set.of(new Card(SPADE, SEVEN),
                                                                new Card(HEART, SEVEN),
                                                                new Card(SPADE, NINE),
                                                                new Card(HEART, NINE),
                                                                new Card(DIAMOND, TEN)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(DIAMOND, THREE),
                                                                new Card(HEART, THREE),
                                                                new Card(SPADE, THREE),
                                                                new Card(CLUB, FIVE),
                                                                new Card(CLUB, SIX))),
                                                new Hand(Set.of(new Card(DIAMOND, TWO),
                                                                new Card(HEART, TWO),
                                                                new Card(SPADE, TWO),
                                                                new Card(CLUB, FIVE),
                                                                new Card(CLUB, SIX)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(DIAMOND, FOUR),
                                                                new Card(HEART, FIVE),
                                                                new Card(HEART, SIX),
                                                                new Card(HEART, SEVEN),
                                                                new Card(HEART, EIGHT))),
                                                new Hand(Set.of(new Card(HEART, THREE),
                                                                new Card(HEART, FOUR),
                                                                new Card(DIAMOND, FIVE),
                                                                new Card(DIAMOND, SIX),
                                                                new Card(DIAMOND, SEVEN)))),
                                // TODO
                                Arguments.of(
                                                new Hand(Set.of(new Card(HEART, TWO),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(DIAMOND, FIVE),
                                                                new Card(DIAMOND, SIX))),
                                                new Hand(Set.of(new Card(DIAMOND, ACE),
                                                                new Card(DIAMOND, TWO),
                                                                new Card(HEART, THREE),
                                                                new Card(HEART, FOUR),
                                                                new Card(HEART, FIVE)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(DIAMOND, TEN),
                                                                new Card(DIAMOND, JACK),
                                                                new Card(HEART, QUEEN),
                                                                new Card(HEART, KING),
                                                                new Card(HEART, ACE))),
                                                new Hand(Set.of(new Card(HEART, NINE),
                                                                new Card(HEART, TEN),
                                                                new Card(HEART, JACK),
                                                                new Card(DIAMOND, QUEEN),
                                                                new Card(DIAMOND, KING)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(CLUB, SEVEN),
                                                                new Card(CLUB, TEN))),
                                                new Hand(Set.of(new Card(SPADE, TWO),
                                                                new Card(SPADE, THREE),
                                                                new Card(SPADE, FOUR),
                                                                new Card(SPADE, SEVEN),
                                                                new Card(SPADE, NINE)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, SEVEN),
                                                                new Card(DIAMOND, SEVEN),
                                                                new Card(HEART, SEVEN),
                                                                new Card(CLUB, TWO),
                                                                new Card(DIAMOND, TWO))),
                                                new Hand(Set.of(new Card(CLUB, SIX),
                                                                new Card(DIAMOND, SIX),
                                                                new Card(HEART, SIX),
                                                                new Card(CLUB, TWO),
                                                                new Card(DIAMOND, TWO)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, NINE),
                                                                new Card(DIAMOND, NINE),
                                                                new Card(HEART, NINE),
                                                                new Card(SPADE, NINE),
                                                                new Card(CLUB, EIGHT))),
                                                new Hand(Set.of(new Card(CLUB, SEVEN),
                                                                new Card(DIAMOND, SEVEN),
                                                                new Card(HEART, SEVEN),
                                                                new Card(SPADE, SEVEN),
                                                                new Card(SPADE, EIGHT)))),
                                Arguments.of(
                                                new Hand(Set.of(new Card(CLUB, FOUR),
                                                                new Card(CLUB, FIVE),
                                                                new Card(CLUB, SIX),
                                                                new Card(CLUB, SEVEN),
                                                                new Card(CLUB, EIGHT))),
                                                new Hand(Set.of(new Card(SPADE, THREE),
                                                                new Card(SPADE, FOUR),
                                                                new Card(SPADE, FIVE),
                                                                new Card(SPADE, SIX),
                                                                new Card(SPADE, SEVEN)))));
        }

}
