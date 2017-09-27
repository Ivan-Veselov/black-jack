package ru.spbau.bachelors2015.blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {
    @Test
    public void rank() throws Exception {
        Card card;
        card = new Card(Suit.CLUBS, CardRank.FOUR);
        assertEquals(card.rank(), CardRank.FOUR);

        card = new Card(Suit.SPADES, CardRank.ACE);
        assertEquals(card.rank(), CardRank.ACE);
    }

    @Test
    public void suit() throws Exception {
        Card card;
        card = new Card(Suit.CLUBS, CardRank.FOUR);
        assertEquals(card.suit(), Suit.CLUBS);

        card = new Card(Suit.SPADES, CardRank.ACE);
        assertEquals(card.suit(), Suit.SPADES);
    }

}