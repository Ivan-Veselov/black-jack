package ru.spbau.bachelors2015.blackjack;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (suit != card.suit) return false;
        return rank == card.rank;
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }

    public static List<Card> fullDeck() {
        List<Card> deck = new ArrayList<>();
        for (CardRank cardRank : CardRank.values()) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(suit, cardRank));
            }
        }
        return deck;
    }
}
