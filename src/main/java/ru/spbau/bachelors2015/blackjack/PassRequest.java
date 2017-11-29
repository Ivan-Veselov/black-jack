package ru.spbau.bachelors2015.blackjack;

import org.jetbrains.annotations.NotNull;

public class PassRequest implements Request {
    @Override
    public Object performOn(@NotNull Game game, int playerId) {
        game.pass(playerId);
        return null;
    }
}
