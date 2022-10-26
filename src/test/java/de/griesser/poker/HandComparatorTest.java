package de.griesser.poker;

import static de.griesser.poker.CardSuit.*;
import static de.griesser.poker.CardValue.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class HandComparatorTest {

        private Comparator<Hand> comparator = new HandComparator();

        @ParameterizedTest(name = "{index}: {1} vs {0}")
        @MethodSource("provideFirstHandWins")
        public void compare_ShouldReturnNegativeValue(Set<Card> cards1, Set<Card> cards2) {
                assertTrue(comparator.compare(new Hand(cards2), new Hand(cards1)) < 0,
                                String.format("%s should loose agains %s", cards2, cards1));
        }

        @ParameterizedTest(name = "{index}: {0} vs {1}")
        @MethodSource("provideEqualHands")
        public void compare_ShouldReturn0(Set<Card> cards1, Set<Card> cards2) {
                assertTrue(comparator.compare(new Hand(cards1), new Hand(cards2)) == 0,
                                String.format("%s against %s should be a draw", cards1, cards2));
        }

        @ParameterizedTest(name = "{index}: {0} vs {1}")
        @MethodSource("provideFirstHandWins")
        public void compare_ShouldReturnPositiveValue(Set<Card> cards1, Set<Card> cards2) {
                assertTrue(comparator.compare(new Hand(cards1), new Hand(cards2)) > 0,
                                String.format("%s should win agains %s", cards1, cards2));
        }

        private static Stream<Arguments> provideEqualHands() {
                return Stream.of(
                                // high card vs high card
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(SPADE, FIVE),
                                                                new Card(CLUB, THREE),
                                                                new Card(SPADE, FOUR),
                                                                new Card(SPADE, SEVEN)),
                                                Set.of(new Card(HEART, TWO),
                                                                new Card(DIAMOND, FIVE),
                                                                new Card(HEART, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(DIAMOND, SEVEN))),
                                // pair vs pair
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(SPADE, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(SPADE, FOUR),
                                                                new Card(SPADE, SIX)),
                                                Set.of(new Card(HEART, TWO),
                                                                new Card(DIAMOND, TWO),
                                                                new Card(HEART, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(DIAMOND, SIX))),
                                // two pairs vs two pairs
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(SPADE, TWO),
                                                                new Card(CLUB, FOUR),
                                                                new Card(SPADE, FOUR),
                                                                new Card(SPADE, SIX)),
                                                Set.of(new Card(HEART, TWO),
                                                                new Card(DIAMOND, TWO),
                                                                new Card(HEART, FOUR),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(DIAMOND, SIX))),
                                // straight ace low vs straight ace low
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(SPADE, FOUR),
                                                                new Card(SPADE, FIVE),
                                                                new Card(SPADE, ACE)),
                                                Set.of(new Card(HEART, TWO),
                                                                new Card(HEART, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(DIAMOND, FIVE),
                                                                new Card(DIAMOND, ACE))),
                                // straight vs straight
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(SPADE, FOUR),
                                                                new Card(SPADE, FIVE),
                                                                new Card(SPADE, SIX)),
                                                Set.of(new Card(HEART, TWO),
                                                                new Card(HEART, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(DIAMOND, FIVE),
                                                                new Card(DIAMOND, SIX))),
                                // flush vs flush
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(CLUB, SEVEN),
                                                                new Card(CLUB, NINE)),
                                                Set.of(new Card(DIAMOND, TWO),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(DIAMOND, SEVEN),
                                                                new Card(DIAMOND, NINE))),
                                // straight flush ace low vs straight flush ace low
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(CLUB, FIVE),
                                                                new Card(CLUB, ACE)),
                                                Set.of(new Card(DIAMOND, TWO),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(DIAMOND, FIVE),
                                                                new Card(DIAMOND, ACE))),
                                // straight flush vs straight flush
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(CLUB, FIVE),
                                                                new Card(CLUB, SIX)),
                                                Set.of(new Card(DIAMOND, TWO),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(DIAMOND, FIVE),
                                                                new Card(DIAMOND, SIX))));
        }

        private static Stream<Arguments> provideFirstHandWins() {
                return Stream.of(
                                // high card vs high card
                                Arguments.of(
                                                Set.of(new Card(SPADE, TWO),
                                                                new Card(SPADE, THREE),
                                                                new Card(SPADE, FIVE),
                                                                new Card(DIAMOND, SIX),
                                                                new Card(DIAMOND, NINE)),
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(HEART, FIVE),
                                                                new Card(HEART, SIX),
                                                                new Card(HEART, EIGHT))),
                                // pair vs high card
                                Arguments.of(
                                                Set.of(new Card(CLUB, TEN),
                                                                new Card(DIAMOND, TEN),
                                                                new Card(HEART, TWO),
                                                                new Card(HEART, THREE),
                                                                new Card(HEART, FOUR)),
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(HEART, FIVE),
                                                                new Card(HEART, SIX),
                                                                new Card(HEART, EIGHT))),
                                // pair vs pair
                                Arguments.of(
                                                Set.of(new Card(CLUB, JACK),
                                                                new Card(DIAMOND, JACK),
                                                                new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR)),
                                                Set.of(new Card(CLUB, TEN),
                                                                new Card(DIAMOND, TEN),
                                                                new Card(HEART, TWO),
                                                                new Card(HEART, THREE),
                                                                new Card(HEART, FOUR))),
                                // two pairs vs pair
                                Arguments.of(
                                                Set.of(new Card(CLUB, SEVEN),
                                                                new Card(DIAMOND, SEVEN),
                                                                new Card(CLUB, NINE),
                                                                new Card(DIAMOND, NINE),
                                                                new Card(HEART, TEN)),
                                                Set.of(new Card(CLUB, JACK),
                                                                new Card(DIAMOND, JACK),
                                                                new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR))),
                                // two pairs vs two pairs
                                Arguments.of(
                                                Set.of(new Card(CLUB, SEVEN),
                                                                new Card(DIAMOND, SEVEN),
                                                                new Card(CLUB, NINE),
                                                                new Card(DIAMOND, NINE),
                                                                new Card(HEART, TEN)),
                                                Set.of(new Card(CLUB, THREE),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(HEART, EIGHT))),
                                // three of a kind vs two pairs
                                Arguments.of(
                                                Set.of(new Card(DIAMOND, TWO),
                                                                new Card(HEART, TWO),
                                                                new Card(SPADE, TWO),
                                                                new Card(CLUB, FIVE),
                                                                new Card(CLUB, SIX)),
                                                Set.of(new Card(CLUB, THREE),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(HEART, EIGHT))),
                                // straight ace low vs three of a kind
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(SPADE, FOUR),
                                                                new Card(SPADE, FIVE),
                                                                new Card(SPADE, ACE)),
                                                Set.of(new Card(DIAMOND, TWO),
                                                                new Card(HEART, TWO),
                                                                new Card(SPADE, TWO),
                                                                new Card(CLUB, FIVE),
                                                                new Card(CLUB, SIX))),
                                // straight vs straight
                                Arguments.of(
                                                Set.of(new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(HEART, FIVE),
                                                                new Card(HEART, SIX),
                                                                new Card(HEART, SEVEN)),
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(SPADE, FOUR),
                                                                new Card(SPADE, FIVE),
                                                                new Card(SPADE, SIX))),

                                // straight vs straight ace low
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(SPADE, FOUR),
                                                                new Card(SPADE, FIVE),
                                                                new Card(SPADE, SIX)),
                                                Set.of(new Card(DIAMOND, ACE),
                                                                new Card(DIAMOND, TWO),
                                                                new Card(HEART, THREE),
                                                                new Card(HEART, FOUR),
                                                                new Card(HEART, FIVE))),
                                // flush vs straight
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(CLUB, SEVEN),
                                                                new Card(CLUB, NINE)),
                                                Set.of(new Card(DIAMOND, TWO),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(HEART, FOUR),
                                                                new Card(HEART, FIVE),
                                                                new Card(HEART, SIX))),
                                // flush vs flush
                                Arguments.of(
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(CLUB, SEVEN),
                                                                new Card(CLUB, NINE)),
                                                Set.of(new Card(DIAMOND, TWO),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(DIAMOND, SEVEN),
                                                                new Card(DIAMOND, EIGHT))),
                                // full house vs flush
                                Arguments.of(
                                                Set.of(new Card(CLUB, SIX),
                                                                new Card(DIAMOND, SIX),
                                                                new Card(HEART, SIX),
                                                                new Card(DIAMOND, TWO),
                                                                new Card(HEART, TWO)),
                                                Set.of(new Card(CLUB, TWO),
                                                                new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(CLUB, SEVEN),
                                                                new Card(CLUB, EIGHT))),
                                // full house vs full house
                                Arguments.of(
                                                Set.of(new Card(CLUB, SIX),
                                                                new Card(DIAMOND, SIX),
                                                                new Card(HEART, SIX),
                                                                new Card(CLUB, TWO),
                                                                new Card(DIAMOND, TWO)),
                                                Set.of(new Card(CLUB, THREE),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(HEART, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(DIAMOND, FOUR))),
                                // four of a kind vs full house
                                Arguments.of(
                                                Set.of(new Card(CLUB, SIX),
                                                                new Card(DIAMOND, SIX),
                                                                new Card(HEART, SIX),
                                                                new Card(SPADE, SIX),
                                                                new Card(CLUB, FIVE)),
                                                Set.of(new Card(CLUB, THREE),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(HEART, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(DIAMOND, FOUR))),
                                // four of a kind vs four of a kind
                                Arguments.of(
                                                Set.of(new Card(CLUB, SEVEN),
                                                                new Card(DIAMOND, SEVEN),
                                                                new Card(HEART, SEVEN),
                                                                new Card(SPADE, SEVEN),
                                                                new Card(CLUB, EIGHT)),
                                                Set.of(new Card(CLUB, SIX),
                                                                new Card(DIAMOND, SIX),
                                                                new Card(HEART, SIX),
                                                                new Card(SPADE, SIX),
                                                                new Card(CLUB, FIVE))),
                                // straight flush ace low vs four of a kind
                                Arguments.of(
                                                Set.of(new Card(DIAMOND, TWO),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(DIAMOND, FIVE),
                                                                new Card(DIAMOND, ACE)),
                                                Set.of(new Card(CLUB, SEVEN),
                                                                new Card(DIAMOND, SEVEN),
                                                                new Card(HEART, SEVEN),
                                                                new Card(SPADE, SEVEN),
                                                                new Card(CLUB, EIGHT))),
                                // straight flush vs straight flush ace low
                                Arguments.of(
                                                Set.of(new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(CLUB, FIVE),
                                                                new Card(CLUB, SIX),
                                                                new Card(CLUB, SEVEN)),
                                                Set.of(new Card(DIAMOND, TWO),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(DIAMOND, FIVE),
                                                                new Card(DIAMOND, ACE))),
                                // straight flush vs straight flush
                                Arguments.of(
                                                Set.of(new Card(CLUB, THREE),
                                                                new Card(CLUB, FOUR),
                                                                new Card(CLUB, FIVE),
                                                                new Card(CLUB, SIX),
                                                                new Card(CLUB, SEVEN)),
                                                Set.of(new Card(DIAMOND, TWO),
                                                                new Card(DIAMOND, THREE),
                                                                new Card(DIAMOND, FOUR),
                                                                new Card(DIAMOND, FIVE),
                                                                new Card(DIAMOND, SIX))));
        }

}
