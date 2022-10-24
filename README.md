# Poker

Determine the winner of 2 poker hands.

A hand is defined by a set of cards.
Each card has a suit (C, D, H, S) and a value (2, 3, 4, ..., J, Q, K, A).

The hand class computes and stores key facts needed to compare hands: 
- the hand type 
- in case hand types a equal a sorted list of card values.

The hand comparator can be used to compare 2 hands or more. 

## Running unit tests

```shell
mvn clean test
```

The test coverage report can be found under target/site/jacoco/de.griesser.poker/index.html