package ru.spbau.bachelors2015.blackjack;

import org.jetbrains.annotations.NotNull;

public class StartedRequest implements Request{
    @Override
    public Object performOn(@NotNull Game game, int playerId) {
        throw new RuntimeException("StartedRequest.performOn should not be called!");
    }
}
