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

        Thread acceptorThread = new Thread(() -> {
            while (playerSockets.size() < 2) {
                try {
                    playerSockets.add(serverSocket.accept());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        acceptorThread.start();

        Game game = new Game();

        while (true) {
            for (int playerId = 0; playerId < playerSockets.size(); playerId++) {
                Socket playerSocket = playerSockets.get(playerId);
                ObjectInputStream inputStream = new ObjectInputStream(playerSocket.getInputStream());
                if (inputStream.available() > 0) {
                    Request request = (Request) inputStream.readObject();
                    Object result;
                    if (request instanceof StartedRequest) {
                        result = playerSockets.size() == 2;
                    }
                    else {
                        result = request.performOn(game, playerId);
                    }
                    ObjectOutputStream outputStream = new ObjectOutputStream(playerSocket.getOutputStream());
                    outputStream.writeObject(result);
                }
            }
        }
    }
}
