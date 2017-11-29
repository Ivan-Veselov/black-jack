package ru.spbau.bachelors2015.blackjack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerCommunicator implements Server{
    private Socket connection;

    private Object sendRequest(final Request request) throws IOException, ClassNotFoundException {
        ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
        outputStream.writeObject(request);

        ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());
        return inputStream.readObject();
    }

    @Override
    public void connect() throws IOException {
        connection = new Socket("localhost", 1234);
    }

    @Override
    public void disconnect() throws IOException {
        connection.close();
    }

    @Override
    public boolean isStarted() {
        return connection.isConnected() || !connection.isClosed();
    }

    // TODO
    @Override
    public Status status() {

        return null;
    }

    // TODO
    @Override
    public void pass() {}

    // TODO
    @Override
    public Card nextCard() {
        return null;
    }

    // TODO
    @Override
    public int myPoints() {
        return 0;
    }

    // TODO
    @Override
    public int hisPoints() {
        return 0;
    }
}
