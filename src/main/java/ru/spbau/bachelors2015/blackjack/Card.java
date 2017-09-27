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
}
