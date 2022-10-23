package de.griesser.poker;

import static de.griesser.poker.CardSuit.*;
import static de.griesser.poker.CardValue.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardValueComparatorTest {

        @ParameterizedTest(name = "{index}: {0} {2} vs {1}")
        @MethodSource("provideFirstCardValueWins")
        public void compare_ShouldReturnNegativeValue(Set<Card> cards, CardValue value1, CardValue value2) {
                assertTrue(new CardValueComparator(cards).compare(value2, value1) < 0,
                                String.format("%s should loose agains %s", value1, value2));
        }

        @ParameterizedTest(name = "{index}: {0} {1} vs {2}")
        @MethodSource("provideEqualCardValues")
        public void compare_ShouldReturn0(Set<Card> cards, CardValue value1, CardValue value2) {
                assertTrue(new CardValueComparator(cards).compare(value1, value2) == 0,
                                String.format("%s against %s should be a draw", value1, value2));
        }

        @ParameterizedTest(name = "{index}: {0} {1} vs {2}")
        @MethodSource("provideFirstCardValueWins")
        public void compare_ShouldReturnPositiveValue(Set<Card> cards, CardValue value1, CardValue value2) {
                assertTrue(new CardValueComparator(cards).compare(value1, value2) > 0,
                                String.format("%s should win agains %s", value1, value2));
        }

        private static Stream<Arguments> provideEqualCardValues() {
                return Stream.of(
                                // pair
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(SPADE, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(SPADE, FOUR),
                                                                new Card(SPADE, SIX)),
                                                TWO, TWO),
                                // two pairs
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(SPADE, TWO),
                                                                new Card(CLUB, FOUR),
                                                                new Card(SPADE, FOUR),
                                                                new Card(SPADE, SIX)),
                                                TWO, TWO),
                                // 3 of a kind
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(DIAMOND, TWO),
                                                                new Card(SPADE, TWO),
                                                                new Card(CLUB, FIVE),
                                                                new Card(CLUB, SIX)),
                                                TWO, TWO),
                                // 4 of a kind
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(DIAMOND, TWO),
                                                                new Card(SPADE, TWO),
                                                                new Card(HEART, TWO),
                                                                new Card(CLUB, SIX)),
                                                TWO, TWO));
        }

        private static Stream<Arguments> provideFirstCardValueWins() {
                return Stream.of(
                                // high card
                                Arguments.of(
                                                Set.of(new Card(SPADE, TWO),
                                                                new Card(SPADE, THREE),
                                                                new Card(SPADE, FIVE),
                                                                new Card(DIAMOND, SIX),
                                                                new Card(DIAMOND, NINE)),
                                                NINE, SIX),
                                // pair
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(DIAMOND, TWO),
                                                                new Card(HEART, TEN),
                                                                new Card(HEART, THREE),
                                                                new Card(HEART, FOUR)),
                                                TWO, TEN),
                                // two pairs
                                Arguments.of(
                                                Set.of(new Card(CLUB, SEVEN),
                                                                new Card(DIAMOND, SEVEN),
                                                                new Card(CLUB, NINE),
                                                                new Card(DIAMOND, NINE),
                                                                new Card(HEART, TEN)),
                                                NINE, SEVEN),
                                // two pairs
                                Arguments.of(
                                                Set.of(new Card(CLUB, SEVEN),
                                                                new Card(DIAMOND, SEVEN),
                                                                new Card(CLUB, NINE),
                                                                new Card(DIAMOND, NINE),
                                                                new Card(HEART, TEN)),
                                                SEVEN, TEN),
                                // three of a kind
                                Arguments.of(
                                                Set.of(new Card(DIAMOND, TWO),
                                                                new Card(HEART, TWO),
                                                                new Card(SPADE, TWO),
                                                                new Card(CLUB, FIVE),
                                                                new Card(CLUB, SIX)),
                                                TWO, FIVE),
                                // four of a kind
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(DIAMOND, TWO),
                                                                new Card(HEART, TWO),
                                                                new Card(SPADE, TWO),
                                                                new Card(CLUB, FIVE)),
                                                TWO, FIVE));
        }

}
