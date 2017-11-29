package ru.spbau.bachelors2015.blackjack;

import org.jetbrains.annotations.NotNull;

public class StatusRequest implements Request {
    @Override
    public Object performOn(@NotNull Game game, int playerId) {
        return game.getStatus(playerId);
    }
}
