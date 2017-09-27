package ru.spbau.bachelors2015.blackjack;

public enum CardRank {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;

    public int value() {
        if (this == ACE) {
            return 11;
        }

        if (ordinal() >= TEN.ordinal()) {
            return 10;
        }

        return ordinal() + 2;
    }
}
