package ru.spbau.bachelors2015.blackjack;

import java.io.IOException;

public interface Server {
    enum Status {YOUR_TURN, WAITING, WIN, LOSE}

    void connect() throws IOException;
    void disconnect() throws IOException;

    boolean isStarted();
    Status status();

    void pass();
    Card nextCard();

    int myPoints();
    int hisPoints();
}
