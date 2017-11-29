package ru.spbau.bachelors2015.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.spbau.bachelors2015.blackjack.Status.*;

public class Game {
    public final static int MAX_POINTS = 21;
    private List<Card> deck;

    private Player[] players;

    // id игрока, кто сходил последним
    // изначальная инициализация -- последний игрок, чтобы следующий игрок(т.е. первый) ходил первым
    // т.е. это сделано для того, чтобы первый игрок всегда ходил бы первым
    private int lastPlayerId;

    public Game(List<Card> deck) {
        this.deck = new ArrayList<>(deck);
        afterInit();
    }

    public Game() {
        deck = Card.fullDeck();

        Collections.shuffle(deck);

        afterInit();
    }

    private void afterInit() {
        // TODO : add constant -- amount of players
        players = new Player[2];
        lastPlayerId = players.length - 1;

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
    public Card nextCard(int playerId) {
        if (nextPlayerTurn() != playerId) {
            return null;
        }

        Card card = pick();
        players[playerId].addCard(card);
        lastPlayerId = playerId;

        return card;
    }

    public Status getStatus(int playerId) {
        if (nextPlayerTurn() == playerId) {
            return YOUR_TURN;
        }

        if (nextPlayerTurn() == -1) {
            if (getWinnerId() == playerId) {
                return WIN;
            } else {
                return LOSE;
            }
        }

        return HIS_TURN;
    }

    // возвращает id игрока, кто будет ходить следующим
    // иначе возвращает -1, если игра закончена
    public int nextPlayerTurn() {
        if (isAllPassed()) {
            return -1;
        }

        return (lastPlayerId + 1) % players.length;
    }

    // true, если все спасовали
    // false, если игра продолжается
    public boolean pass(int playerId) {
        players[playerId].pass();

        return isAllPassed();
    }

    public List<Card> getPlayersHand(int playerId) {
        return players[playerId].getHand();
    }

    private Card pick() {
        Card res = deck.get(0);
        deck.remove(0);

        return res;
    }

    public boolean isAllPassed() {
        return players[0].isPassed() && players[1].isPassed();
    }

    public int getPlayerPoints(int playerId) {
        return players[playerId].points();
    }

    // -1 если ничья иначе id игрока
    public int getWinnerId() {
        int firstPoints = getPlayerPoints(0);
        int secondPoints = getPlayerPoints(1);;

        if ((firstPoints > MAX_POINTS && secondPoints > MAX_POINTS) ||
                firstPoints == secondPoints) {
            return -1;
        }

        if (firstPoints <= MAX_POINTS && secondPoints > MAX_POINTS) {
            return 0;
        }

        if (firstPoints > MAX_POINTS) {
            return 1;
        }

        if (firstPoints < secondPoints) {
            return 1;
        } else {
            return 0;
        }
    }
}