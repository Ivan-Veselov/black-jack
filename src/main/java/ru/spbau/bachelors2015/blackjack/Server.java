package ru.spbau.bachelors2015.blackjack;

public interface Server {
    enum Status {YOUR_TURN, WAITING, WIN, LOSE}

    void connect();
    void disconnect();

    boolean isStarted();
    Status status();

    void pass();
    Card nextCard();

    int myPoints();
    int hisPoints();
}
