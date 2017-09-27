package ru.spbau.bachelors2015.blackjack;

public class Card {
    private Suit suit;

    private CardRank rank;

    public Card(Suit suit, CardRank cardRank) {
        this.suit = suit;
        this.rank = cardRank;
    }

    public CardRank rank() {
        return rank;
    }

    public Suit suit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank().toString() + " of " + suit().toString();
    }
}
