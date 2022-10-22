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

        @ParameterizedTest(name = "{index}: {0} vs {1}")
        @MethodSource("provideFirstHandWins")
        public void compare_ShouldReturnNegativeValue(Set<Card<CardValue>> cards1, Set<Card<CardValue>> cards2) {
                assertTrue(comparator.compare(new Hand(cards2), new Hand(cards1)) < 0,
                                String.format("%s should loose agains %s", cards1, cards2));
        }

        @ParameterizedTest(name = "{index}: {0} vs {1}")
        @MethodSource("provideEqualsHands")
        public void compare_ShouldReturn0(Set<Card<CardValue>> cards1, Set<Card<CardValue>> cards2) {
                assertTrue(comparator.compare(new Hand(cards1), new Hand(cards2)) == 0,
                                String.format("%s against %s should be a draw", cards1, cards2));
        }

        @ParameterizedTest(name = "{index}: {0} vs {1}")
        @MethodSource("provideFirstHandWins")
        public void compare_ShouldReturnPositiveValue(Set<Card<CardValue>> cards1, Set<Card<CardValue>> cards2) {
                assertTrue(comparator.compare(new Hand(cards1), new Hand(cards2)) > 0,
                                String.format("%s should win agains %s", cards1, cards2));
        }

        private static Stream<Arguments> provideEqualsHands() {
                return Stream.of(
                                // high card vs high card
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(SPADE, FIVE),
                                                                new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(SPADE, FOUR),
                                                                new Card<CardValue>(SPADE, SIX)),
                                                Set.of(new Card<CardValue>(HEART, TWO),
                                                                new Card<CardValue>(DIAMOND, FIVE),
                                                                new Card<CardValue>(HEART, THREE),
                                                                new Card<CardValue>(DIAMOND, FOUR),
                                                                new Card<CardValue>(DIAMOND, SIX))),
                                // pair vs pair
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(SPADE, TWO),
                                                                new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(SPADE, FOUR),
                                                                new Card<CardValue>(SPADE, SIX)),
                                                Set.of(new Card<CardValue>(HEART, TWO),
                                                                new Card<CardValue>(DIAMOND, TWO),
                                                                new Card<CardValue>(HEART, THREE),
                                                                new Card<CardValue>(DIAMOND, FOUR),
                                                                new Card<CardValue>(DIAMOND, SIX))),
                                // two pairs vs two pairs
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(SPADE, TWO),
                                                                new Card<CardValue>(CLUB, FOUR),
                                                                new Card<CardValue>(SPADE, FOUR),
                                                                new Card<CardValue>(SPADE, SIX)),
                                                Set.of(new Card<CardValue>(HEART, TWO),
                                                                new Card<CardValue>(DIAMOND, TWO),
                                                                new Card<CardValue>(HEART, FOUR),
                                                                new Card<CardValue>(DIAMOND, FOUR),
                                                                new Card<CardValue>(DIAMOND, SIX))),
                                // straight vs straight
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(SPADE, FOUR),
                                                                new Card<CardValue>(SPADE, FIVE),
                                                                new Card<CardValue>(SPADE, SIX)),
                                                Set.of(new Card<CardValue>(HEART, TWO),
                                                                new Card<CardValue>(HEART, THREE),
                                                                new Card<CardValue>(DIAMOND, FOUR),
                                                                new Card<CardValue>(DIAMOND, FIVE),
                                                                new Card<CardValue>(DIAMOND, SIX))),
                                // flush vs flush
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(CLUB, FOUR),
                                                                new Card<CardValue>(CLUB, SEVEN),
                                                                new Card<CardValue>(CLUB, NINE)),
                                                Set.of(new Card<CardValue>(DIAMOND, TWO),
                                                                new Card<CardValue>(DIAMOND, THREE),
                                                                new Card<CardValue>(DIAMOND, FOUR),
                                                                new Card<CardValue>(DIAMOND, SEVEN),
                                                                new Card<CardValue>(DIAMOND, NINE))),
                                // straight flush vs straight flush
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(CLUB, FOUR),
                                                                new Card<CardValue>(CLUB, FIVE),
                                                                new Card<CardValue>(CLUB, SIX)),
                                                Set.of(new Card<CardValue>(DIAMOND, TWO),
                                                                new Card<CardValue>(DIAMOND, THREE),
                                                                new Card<CardValue>(DIAMOND, FOUR),
                                                                new Card<CardValue>(DIAMOND, FIVE),
                                                                new Card<CardValue>(DIAMOND, SIX))));
        }

        private static Stream<Arguments> provideFirstHandWins() {
                return Stream.of(
                                // high card vs high card
                                Arguments.of(
                                                Set.of(new Card<CardValue>(SPADE, TWO),
                                                                new Card<CardValue>(SPADE, THREE),
                                                                new Card<CardValue>(SPADE, FIVE),
                                                                new Card<CardValue>(DIAMOND, SIX),
                                                                new Card<CardValue>(DIAMOND, NINE)),
                                                Set.of(new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(DIAMOND, THREE),
                                                                new Card<CardValue>(HEART, FIVE),
                                                                new Card<CardValue>(HEART, SIX),
                                                                new Card<CardValue>(HEART, EIGHT))),
                                // pair vs high card
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, TEN),
                                                                new Card<CardValue>(DIAMOND, TEN),
                                                                new Card<CardValue>(HEART, TWO),
                                                                new Card<CardValue>(HEART, THREE),
                                                                new Card<CardValue>(HEART, FOUR)),
                                                Set.of(new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(DIAMOND, THREE),
                                                                new Card<CardValue>(HEART, FIVE),
                                                                new Card<CardValue>(HEART, SIX),
                                                                new Card<CardValue>(HEART, EIGHT))),
                                // pair vs pair
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, JACK),
                                                                new Card<CardValue>(DIAMOND, JACK),
                                                                new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(CLUB, FOUR)),
                                                Set.of(new Card<CardValue>(CLUB, TEN),
                                                                new Card<CardValue>(DIAMOND, TEN),
                                                                new Card<CardValue>(HEART, TWO),
                                                                new Card<CardValue>(HEART, THREE),
                                                                new Card<CardValue>(HEART, FOUR))),
                                // two pairs vs pair
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, SEVEN),
                                                                new Card<CardValue>(DIAMOND, SEVEN),
                                                                new Card<CardValue>(CLUB, NINE),
                                                                new Card<CardValue>(DIAMOND, NINE),
                                                                new Card<CardValue>(HEART, TEN)),
                                                Set.of(new Card<CardValue>(CLUB, JACK),
                                                                new Card<CardValue>(DIAMOND, JACK),
                                                                new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(CLUB, FOUR))),
                                // two pairs vs two pairs
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, SEVEN),
                                                                new Card<CardValue>(DIAMOND, SEVEN),
                                                                new Card<CardValue>(CLUB, NINE),
                                                                new Card<CardValue>(DIAMOND, NINE),
                                                                new Card<CardValue>(HEART, TEN)),
                                                Set.of(new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(DIAMOND, THREE),
                                                                new Card<CardValue>(CLUB, FOUR),
                                                                new Card<CardValue>(DIAMOND, FOUR),
                                                                new Card<CardValue>(HEART, EIGHT))),
                                // three of a kind vs two pairs
                                Arguments.of(
                                                Set.of(new Card<CardValue>(DIAMOND, TWO),
                                                                new Card<CardValue>(HEART, TWO),
                                                                new Card<CardValue>(SPADE, TWO),
                                                                new Card<CardValue>(CLUB, FIVE),
                                                                new Card<CardValue>(CLUB, SIX)),
                                                Set.of(new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(DIAMOND, THREE),
                                                                new Card<CardValue>(CLUB, FOUR),
                                                                new Card<CardValue>(DIAMOND, FOUR),
                                                                new Card<CardValue>(HEART, EIGHT))),
                                // straight vs three of a kind
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(SPADE, FOUR),
                                                                new Card<CardValue>(SPADE, FIVE),
                                                                new Card<CardValue>(SPADE, ACE)),
                                                Set.of(new Card<CardValue>(DIAMOND, TWO),
                                                                new Card<CardValue>(HEART, TWO),
                                                                new Card<CardValue>(SPADE, TWO),
                                                                new Card<CardValue>(CLUB, FIVE),
                                                                new Card<CardValue>(CLUB, SIX))),
                                // straight vs three of a kind
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(SPADE, FOUR),
                                                                new Card<CardValue>(SPADE, FIVE),
                                                                new Card<CardValue>(SPADE, SIX)),
                                                Set.of(new Card<CardValue>(DIAMOND, TWO),
                                                                new Card<CardValue>(HEART, TWO),
                                                                new Card<CardValue>(SPADE, TWO),
                                                                new Card<CardValue>(CLUB, FIVE),
                                                                new Card<CardValue>(CLUB, SIX))),
                                // straight vs straight
                                Arguments.of(
                                                Set.of(new Card<CardValue>(DIAMOND, THREE),
                                                                new Card<CardValue>(DIAMOND, FOUR),
                                                                new Card<CardValue>(HEART, FIVE),
                                                                new Card<CardValue>(HEART, SIX),
                                                                new Card<CardValue>(HEART, SEVEN)),
                                                Set.of(new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(SPADE, FOUR),
                                                                new Card<CardValue>(SPADE, FIVE),
                                                                new Card<CardValue>(SPADE, SIX))),

                                // straight vs straight
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(SPADE, FOUR),
                                                                new Card<CardValue>(SPADE, FIVE),
                                                                new Card<CardValue>(SPADE, SIX)),
                                                Set.of(new Card<CardValue>(DIAMOND, ACE),
                                                                new Card<CardValue>(DIAMOND, TWO),
                                                                new Card<CardValue>(HEART, THREE),
                                                                new Card<CardValue>(HEART, FOUR),
                                                                new Card<CardValue>(HEART, FIVE))),
                                // flush vs straight
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(CLUB, FOUR),
                                                                new Card<CardValue>(CLUB, SEVEN),
                                                                new Card<CardValue>(CLUB, NINE)),
                                                Set.of(new Card<CardValue>(DIAMOND, TWO),
                                                                new Card<CardValue>(DIAMOND, THREE),
                                                                new Card<CardValue>(HEART, FOUR),
                                                                new Card<CardValue>(HEART, FIVE),
                                                                new Card<CardValue>(HEART, SIX))),
                                // flush vs flush
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(CLUB, FOUR),
                                                                new Card<CardValue>(CLUB, SEVEN),
                                                                new Card<CardValue>(CLUB, NINE)),
                                                Set.of(new Card<CardValue>(DIAMOND, TWO),
                                                                new Card<CardValue>(DIAMOND, THREE),
                                                                new Card<CardValue>(DIAMOND, FOUR),
                                                                new Card<CardValue>(DIAMOND, SEVEN),
                                                                new Card<CardValue>(DIAMOND, EIGHT))),
                                // full house vs flush
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, SIX),
                                                                new Card<CardValue>(DIAMOND, SIX),
                                                                new Card<CardValue>(HEART, SIX),
                                                                new Card<CardValue>(DIAMOND, TWO),
                                                                new Card<CardValue>(HEART, TWO)),
                                                Set.of(new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(CLUB, FOUR),
                                                                new Card<CardValue>(CLUB, SEVEN),
                                                                new Card<CardValue>(CLUB, EIGHT))),
                                // full house vs full house
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, SIX),
                                                                new Card<CardValue>(DIAMOND, SIX),
                                                                new Card<CardValue>(HEART, SIX),
                                                                new Card<CardValue>(CLUB, TWO),
                                                                new Card<CardValue>(DIAMOND, TWO)),
                                                Set.of(new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(DIAMOND, THREE),
                                                                new Card<CardValue>(HEART, THREE),
                                                                new Card<CardValue>(CLUB, FOUR),
                                                                new Card<CardValue>(DIAMOND, FOUR))),
                                // four of a kind vs full house
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, SIX),
                                                                new Card<CardValue>(DIAMOND, SIX),
                                                                new Card<CardValue>(HEART, SIX),
                                                                new Card<CardValue>(SPADE, SIX),
                                                                new Card<CardValue>(CLUB, FIVE)),
                                                Set.of(new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(DIAMOND, THREE),
                                                                new Card<CardValue>(HEART, THREE),
                                                                new Card<CardValue>(CLUB, FOUR),
                                                                new Card<CardValue>(DIAMOND, FOUR))),
                                // four of a kind vs four of a kind
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, SEVEN),
                                                                new Card<CardValue>(DIAMOND, SEVEN),
                                                                new Card<CardValue>(HEART, SEVEN),
                                                                new Card<CardValue>(SPADE, SEVEN),
                                                                new Card<CardValue>(CLUB, EIGHT)),
                                                Set.of(new Card<CardValue>(CLUB, SIX),
                                                                new Card<CardValue>(DIAMOND, SIX),
                                                                new Card<CardValue>(HEART, SIX),
                                                                new Card<CardValue>(SPADE, SIX),
                                                                new Card<CardValue>(CLUB, FIVE))),
                                // straight flush vs four of a kind
                                Arguments.of(
                                                Set.of(new Card<CardValue>(DIAMOND, TWO),
                                                                new Card<CardValue>(DIAMOND, THREE),
                                                                new Card<CardValue>(DIAMOND, FOUR),
                                                                new Card<CardValue>(DIAMOND, FIVE),
                                                                new Card<CardValue>(DIAMOND, SIX)),
                                                Set.of(new Card<CardValue>(CLUB, SEVEN),
                                                                new Card<CardValue>(DIAMOND, SEVEN),
                                                                new Card<CardValue>(HEART, SEVEN),
                                                                new Card<CardValue>(SPADE, SEVEN),
                                                                new Card<CardValue>(CLUB, EIGHT))),
                                // straight flush vs straight flush
                                Arguments.of(
                                                Set.of(new Card<CardValue>(CLUB, THREE),
                                                                new Card<CardValue>(CLUB, FOUR),
                                                                new Card<CardValue>(CLUB, FIVE),
                                                                new Card<CardValue>(CLUB, SIX),
                                                                new Card<CardValue>(CLUB, SEVEN)),
                                                Set.of(new Card<CardValue>(DIAMOND, TWO),
                                                                new Card<CardValue>(DIAMOND, THREE),
                                                                new Card<CardValue>(DIAMOND, FOUR),
                                                                new Card<CardValue>(DIAMOND, FIVE),
                                                                new Card<CardValue>(DIAMOND, SIX))));
        }

}
