package de.griesser.poker;

import static de.griesser.poker.CardSuit.*;
import static de.griesser.poker.ResolvedCardValue.*;
import static de.griesser.poker.HandType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class HandTypeTest {

        @ParameterizedTest(name = "{index}: {0} -> {1}")
        @MethodSource("provideHandAndExpectedType")
        public void getHandType_ShouldReturnAppropriateValue(Set<Card<ResolvedCardValue>> cards, HandType expected) {
                assertEquals(expected, HandType.getHandType(cards));
        }

        private static Stream<Arguments> provideHandAndExpectedType() {
                return Stream.of(
                                Arguments.of(
                                                Set.of(new Card<ResolvedCardValue>(SPADE, TWO),
                                                                new Card<ResolvedCardValue>(SPADE, THREE),
                                                                new Card<ResolvedCardValue>(SPADE, FIVE),
                                                                new Card<ResolvedCardValue>(DIAMOND, SIX),
                                                                new Card<ResolvedCardValue>(DIAMOND, NINE)),
                                                HIGH_CARD),
                                Arguments.of(
                                                Set.of(new Card<ResolvedCardValue>(SPADE, KING),
                                                                new Card<ResolvedCardValue>(SPADE, ACE),
                                                                new Card<ResolvedCardValue>(SPADE, TWO),
                                                                new Card<ResolvedCardValue>(DIAMOND, THREE),
                                                                new Card<ResolvedCardValue>(DIAMOND, FOUR)),
                                                HIGH_CARD),
                                Arguments.of(
                                                Set.of(new Card<ResolvedCardValue>(CLUB, JACK),
                                                                new Card<ResolvedCardValue>(DIAMOND, JACK),
                                                                new Card<ResolvedCardValue>(CLUB, TWO),
                                                                new Card<ResolvedCardValue>(CLUB, THREE),
                                                                new Card<ResolvedCardValue>(CLUB, FOUR)),
                                                PAIR),
                                Arguments.of(
                                                Set.of(new Card<ResolvedCardValue>(CLUB, SEVEN),
                                                                new Card<ResolvedCardValue>(DIAMOND, SEVEN),
                                                                new Card<ResolvedCardValue>(CLUB, NINE),
                                                                new Card<ResolvedCardValue>(DIAMOND, NINE),
                                                                new Card<ResolvedCardValue>(HEART, TEN)),
                                                TWO_PAIRS),
                                Arguments.of(
                                                Set.of(new Card<ResolvedCardValue>(DIAMOND, TWO),
                                                                new Card<ResolvedCardValue>(HEART, TWO),
                                                                new Card<ResolvedCardValue>(SPADE, TWO),
                                                                new Card<ResolvedCardValue>(CLUB, FIVE),
                                                                new Card<ResolvedCardValue>(CLUB, SIX)),
                                                THREE_OF_A_KIND),
                                Arguments.of(
                                                Set.of(new Card<ResolvedCardValue>(DIAMOND, THREE),
                                                                new Card<ResolvedCardValue>(DIAMOND, FOUR),
                                                                new Card<ResolvedCardValue>(HEART, FIVE),
                                                                new Card<ResolvedCardValue>(HEART, SIX),
                                                                new Card<ResolvedCardValue>(HEART, SEVEN)),
                                                STRAIGHT),
                                Arguments.of(
                                                Set.of(new Card<ResolvedCardValue>(DIAMOND, ONE),
                                                                new Card<ResolvedCardValue>(DIAMOND, TWO),
                                                                new Card<ResolvedCardValue>(HEART, THREE),
                                                                new Card<ResolvedCardValue>(HEART, FOUR),
                                                                new Card<ResolvedCardValue>(HEART, FIVE)),
                                                STRAIGHT),
                                Arguments.of(
                                                Set.of(new Card<ResolvedCardValue>(DIAMOND, TEN),
                                                                new Card<ResolvedCardValue>(DIAMOND, JACK),
                                                                new Card<ResolvedCardValue>(HEART, QUEEN),
                                                                new Card<ResolvedCardValue>(HEART, KING),
                                                                new Card<ResolvedCardValue>(HEART, ACE)),
                                                STRAIGHT),
                                Arguments.of(
                                                Set.of(new Card<ResolvedCardValue>(CLUB, TWO),
                                                                new Card<ResolvedCardValue>(CLUB, THREE),
                                                                new Card<ResolvedCardValue>(CLUB, FOUR),
                                                                new Card<ResolvedCardValue>(CLUB, SEVEN),
                                                                new Card<ResolvedCardValue>(CLUB, NINE)),
                                                FLUSH),
                                Arguments.of(
                                                Set.of(new Card<ResolvedCardValue>(CLUB, SIX),
                                                                new Card<ResolvedCardValue>(DIAMOND, SIX),
                                                                new Card<ResolvedCardValue>(HEART, SIX),
                                                                new Card<ResolvedCardValue>(CLUB, TWO),
                                                                new Card<ResolvedCardValue>(DIAMOND, TWO)),
                                                FULL_HOUSE),
                                Arguments.of(
                                                Set.of(new Card<ResolvedCardValue>(CLUB, SEVEN),
                                                                new Card<ResolvedCardValue>(DIAMOND, SEVEN),
                                                                new Card<ResolvedCardValue>(HEART, SEVEN),
                                                                new Card<ResolvedCardValue>(SPADE, SEVEN),
                                                                new Card<ResolvedCardValue>(CLUB, EIGHT)),
                                                FOUR_OF_A_KIND),
                                Arguments.of(
                                                Set.of(new Card<ResolvedCardValue>(CLUB, THREE),
                                                                new Card<ResolvedCardValue>(CLUB, FOUR),
                                                                new Card<ResolvedCardValue>(CLUB, FIVE),
                                                                new Card<ResolvedCardValue>(CLUB, SIX),
                                                                new Card<ResolvedCardValue>(CLUB, SEVEN)),
                                                STRAIGHT_FLUSH));
        }

}
