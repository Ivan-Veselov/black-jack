package ru.spbau.bachelors2015.blackjack;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static ru.spbau.bachelors2015.blackjack.Suit.*;
import static ru.spbau.bachelors2015.blackjack.CardRank.*;

public class GameTest {
    private List<Card> deck = Arrays.asList(
            new Card(SPADES, TWO),
            new Card(HEARTS, THREE),
            new Card(HEARTS, FIVE),
            new Card(HEARTS, SEVEN),
            new Card(SPADES, TEN),
            new Card(HEARTS, TEN));

    @Test
    public void nextCard() throws Exception {
        Game game = new Game(deck);
        Card f = game.nextCard();
        assertEquals(f, new Card(SPADES, TEN));
    }

    @Test
    public void pass() throws Exception {
        Game game = new Game(deck);
        assertEquals(12, game.computerPoints());
        assertEquals(5, game.playerPoints());
        game.pass();
        assertEquals(22, game.computerPoints());
        assertEquals(5, game.playerPoints());
    }

    @Test
    public void complicated() throws Exception {
        Game game = new Game(deck);
        game.nextCard();
        game.pass();
        assertEquals(22, game.computerPoints());
        assertEquals(15, game.playerPoints());
    }
}