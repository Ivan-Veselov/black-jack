package ru.spbau.bachelors2015.blackjack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerCommunicator implements Server{
    private Socket connection;

    // TODO: add reasonable exception handling
    private Object sendRequest(final Request request) {
        try {

            ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
            outputStream.writeObject(request);

            ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());
            return inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    @Override
    public Status status() {
        return (Status) sendRequest(new StatusRequest());
    }

    @Override
    public void pass() {
        sendRequest(new PassRequest());
    }

    @Override
    public Card nextCard() {
        return (Card) sendRequest(new NextCardRequest());
    }

    @Override
    public int myPoints() {
        return (int) sendRequest(new MyPointsRequest());
    }

    @Override
    public int hisPoints() {
        return (int) sendRequest(new HisPointsRequest());
    }
}
