package ru.spbau.bachelors2015.blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardRankTest {
    @Test
    public void value() throws Exception {
        assertEquals(CardRank.TWO.value(), 2);
        assertEquals(CardRank.THREE.value(), 3);
        assertEquals(CardRank.FOUR.value(), 4);
        assertEquals(CardRank.FIVE.value(), 5);
        assertEquals(CardRank.SIX.value(), 6);
        assertEquals(CardRank.SEVEN.value(), 7);
        assertEquals(CardRank.EIGHT.value(), 8);
        assertEquals(CardRank.NINE.value(), 9);
        assertEquals(CardRank.TEN.value(), 10);
        assertEquals(CardRank.JACK.value(), 10);
        assertEquals(CardRank.QUEEN.value(), 10);
        assertEquals(CardRank.KING.value(), 10);
        assertEquals(CardRank.ACE.value(), 11);
    }
}