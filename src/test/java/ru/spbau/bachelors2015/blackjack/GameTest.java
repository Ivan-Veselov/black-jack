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

    private List<Card> deckWithAces = Arrays.asList(
            new Card(SPADES, ACE),
            new Card(HEARTS, ACE),
            new Card(DIAMONDS, ACE),
            new Card(CLUBS, ACE)
    );

    @Test
    public void testScore() {
        Game game = new Game(deck);
        List<Card> hand = game.getPlayersHand(0);

        int score = game.getPlayerPoints(0);
        int expected = hand.get(0).rank().value() + hand.get(1).rank().value();
        assertEquals(expected, score);
    }

    @Test
    public void testScoreWithAces() {
        Game game = new Game(deckWithAces);

        int score1 = game.getPlayerPoints(0);
        int score2 = game.getPlayerPoints(1);
        assertEquals(2, score1);
        assertEquals(2, score2);
    }

    @Test
    public void drawTest() {
        Game game = new Game(deckWithAces);

        assertEquals(-1, game.getWinnerId());
    }

    @Test
    public void getWinnerTest() {
        Game game = new Game(deckWithAces);


    }
}