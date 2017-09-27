package ru.spbau.bachelors2015.blackjack;

public class Card {
    public CardRank rank() {
        return null;
    }

    public Suit suit() {
        return null;
    }

    @Override
    public String toString() {
        return rank().toString() + " of " + suit().toString();
    }
}
