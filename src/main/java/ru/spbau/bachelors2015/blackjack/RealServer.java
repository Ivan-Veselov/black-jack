package ru.spbau.bachelors2015.blackjack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RealServer {
    public static final int PORT_NUMBER = 1234;
    private static final int PLAYER_COUNT = 2;

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
        List<Socket> playerSockets = new ArrayList<>();

        while (playerSockets.size() < 2) {
            try {
                playerSockets.add(serverSocket.accept());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Game game = new Game();
        final List<ObjectOutputStream> outputs = new ArrayList<>();
        final List<ObjectInputStream> inputs = new ArrayList<>();
        for (Socket socket : playerSockets) {
            inputs.add(new ObjectInputStream(socket.getInputStream()));
            outputs.add(new ObjectOutputStream(socket.getOutputStream()));
        }

        while (true) {
            for (int playerId = 0; playerId < playerSockets.size(); playerId++) {
                ObjectInputStream inputStream = inputs.get(playerId);
                if (inputStream.available() > 0) {
                    Request request = (Request) inputStream.readObject();
                    Object result;
                    if (request instanceof IsStartedRequest) {
                        result = playerSockets.size() == 2;
                    } else {
                        result = request.performOn(game, playerId);
                    }
                    ObjectOutputStream outputStream = outputs.get(playerId);
                    outputStream.writeObject(result);
                }
            }
        }
    }
}
