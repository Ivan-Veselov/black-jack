package ru.spbau.bachelors2015.blackjack;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static ru.spbau.bachelors2015.blackjack.Status.*;
import static ru.spbau.bachelors2015.blackjack.Suit.*;
import static ru.spbau.bachelors2015.blackjack.CardRank.*;

public class GameTest {
    private List<Card> deck = Arrays.asList(
            new Card(SPADES, TWO),
            new Card(HEARTS, THREE),
            new Card(HEARTS, FIVE),
            new Card(HEARTS, SEVEN),
            new Card(SPADES, TEN),
            new Card(HEARTS, TEN),
            new Card(CLUBS, TEN),
            new Card(CLUBS, SEVEN),
            new Card(CLUBS, FIVE));

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
        Game game = new Game(deck);

        assertEquals(1, game.getWinnerId());
        game.nextCard(0);
        assertEquals(0, game.getWinnerId());
    }

    @Test
    public void getPlayerTurnTest() {
        Game game = new Game(deck);

        assertEquals(0, game.nextPlayerTurn());
        assertEquals(null, game.nextCard(1));

        game.nextCard(0);

        assertEquals(1, game.nextPlayerTurn());
        assertEquals(null, game.nextCard(0));

        game.nextCard(1);

        assertEquals(0, game.nextPlayerTurn());
        assertEquals(null, game.nextCard(1));

        game.pass(0);

        assertEquals(1, game.nextPlayerTurn());
        assertEquals(null, game.nextCard(0));

        game.nextCard(1);

        assertEquals(1, game.nextPlayerTurn());
        assertEquals(null, game.nextCard(0));

        game.pass(0); // double pass
        game.pass(1);

        assertEquals(-1, game.nextPlayerTurn());
        assertEquals(null, game.nextCard(0));
        assertEquals(null, game.nextCard(1));
    }

    @Test
    public void getStatusTest() {
        Game game = new Game(deck);

        assertEquals(YOUR_TURN, game.getStatus(0));
        assertEquals(HIS_TURN, game.getStatus(1));

        game.nextCard(0);

        assertEquals(YOUR_TURN, game.getStatus(1));
        assertEquals(HIS_TURN, game.getStatus(0));

        game.nextCard(1);

        assertEquals(YOUR_TURN, game.getStatus(0));
        assertEquals(HIS_TURN, game.getStatus(1));

        game.pass(0);

        assertEquals(YOUR_TURN, game.getStatus(1));
        assertEquals(HIS_TURN, game.getStatus(0));

        game.nextCard(1);

        assertEquals(YOUR_TURN, game.getStatus(1));
        assertEquals(HIS_TURN, game.getStatus(0));

        game.pass(1);

        assertEquals(LOSE, game.getStatus(1));
        assertEquals(WIN, game.getStatus(0));
    }

    @Test
    public void gameDrawTest() {
        Game game = new Game(deckWithAces);

        game.pass(0);
        game.pass(1);

        assertEquals(DRAW, game.getStatus(1));
        assertEquals(DRAW, game.getStatus(0));
    }
}