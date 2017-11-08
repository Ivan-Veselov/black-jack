package ru.spbau.bachelors2015.blackjack;

public class ServerCommunicator implements Server{
    @Override
    public void connect() {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean isStarted() {
        return true;
    }

    @Override
    public Status status() {
        return null;
    }

    @Override
    public void pass() {

    }

    @Override
    public Card nextCard() {
        return null;
    }

    @Override
    public int myPoints() {
        return 0;
    }

    @Override
    public int hisPoints() {
        return 0;
    }
}
