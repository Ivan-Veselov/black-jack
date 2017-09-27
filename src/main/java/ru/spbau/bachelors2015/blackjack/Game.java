package ru.spbau.bachelors2015.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Game {
    final int MAX_POINTS = 21;

    private List<Card> deck;

    private List<Card> playerHand;

    private List<Card> computerHand;

    public Game(List<Card> deck) {
        this.deck = new ArrayList<>(deck);
        playerHand = new ArrayList<>();
        computerHand = new ArrayList<>();

        playerHand.add(pick());
        playerHand.add(pick());
        computerHand.add(pick());
        computerHand.add(pick());
    }

    public Card nextCard() {
        Card card = pick();
        playerHand.add(card);
        computerMove();

        return card;
    }

    public void pass() {
        while (computerMove());
    }

    public int playerPoints() {
        return points(playerHand);
    }

    public int computerPoints() {
        return points(computerHand);
    }

    private Card pick() {
        Card res = deck.get(0);
        deck.remove(0);

        return res;
    }

    private boolean computerMove() {
        final int THRESHOLD = 17;

        if (computerPoints() < THRESHOLD) {
            computerHand.add(pick());
            return true;
        }

        return false;
    }

    private int points(List<Card> cards) {
        int ps = cards.stream().map(Card::rank).map(CardRank::value).mapToInt(i -> i).sum();

        if (ps > MAX_POINTS) {
            ps -= 10 * cards.stream().map(Card::rank).filter(r -> r == CardRank.ACE).count();
        }

        return ps;
    }
}
