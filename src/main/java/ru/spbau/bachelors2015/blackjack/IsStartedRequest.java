package ru.spbau.bachelors2015.blackjack;

import org.jetbrains.annotations.NotNull;

public class IsStartedRequest implements Request {
    @Override
    public Object performOn(@NotNull Game game, int playerId) {
        throw new RuntimeException("IsStartedRequest.performOn should not be called!");
    }
}
