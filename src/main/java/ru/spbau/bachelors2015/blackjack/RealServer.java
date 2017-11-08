package ru.spbau.bachelors2015.blackjack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RealServer {
    private static final int PORT_NUMBER = 1234;
    private static final int PLAYER_COUNT = 2;

    public void main() throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
        List<Socket> playerSockets = new ArrayList<>();
        for (int i = 0; i < PLAYER_COUNT; i++) {
            playerSockets.add(serverSocket.accept());
        }
        while (true) {
            for (Socket playerSocket : playerSockets) {
                ObjectInputStream inputStream = new ObjectInputStream(playerSocket.getInputStream());
                if (inputStream.available() > 0) {
                    Request request = (Request) inputStream.readObject();
                    Object result = request.handle();
                    ObjectOutputStream outputStream = new ObjectOutputStream(playerSocket.getOutputStream());
                    outputStream.writeObject(result);
                }
            }
        }

    }
}
