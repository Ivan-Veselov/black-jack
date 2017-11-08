package ru.spbau.bachelors2015.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand;
    private boolean isPassed;

    public Player() {
        hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
        // TODO count points
    }

    public void pass() {
        isPassed = true;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int points() {
        int ps = hand.stream().map(Card::rank).map(CardRank::value).mapToInt(i -> i).sum();

        if (ps > Game.getMaxPoints()) {
            ps -= 10 * hand.stream().map(Card::rank).filter(r -> r == CardRank.ACE).count();
        }

        return ps;
    }
}