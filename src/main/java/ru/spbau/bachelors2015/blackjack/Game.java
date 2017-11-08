package ru.spbau.bachelors2015.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private List<Card> deck;

    private Player[] players;

    public Game(List<Card> deck) {
        this.deck = new ArrayList<>(deck);
        afterInit();
    }

    public Game() {
        deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (CardRank rank : CardRank.values()) {
                deck.add(new Card(suit, rank));
            }
        }

        Collections.shuffle(deck);

        afterInit();
    }

    private void afterInit() {
        players = new Player[2];

        for (int i = 0; i < 2; i++) {
            players[i] = new Player();
        }

        for (int i = 0; i < 2; i++) {
            players[0].addCard(pick());
            players[1].addCard(pick());
        }
    }

    // playerIndex == 0 -- первый игрок
    // playerIndex == 1 -- второй игрок
    public Card nextCard(int playerIndex) {
        Card card = pick();
        players[playerIndex].addCard(card);

        return card;
    }

    public void pass(int playerIndex) {
        players[playerIndex].pass();
    }

    private Card pick() {
        Card res = deck.get(0);
        deck.remove(0);

        return res;
    }

    public boolean isAllPassed() {
        return players[0].isPassed() && players[1].isPassed();
    }

    // понимать кто из игроков победил
}