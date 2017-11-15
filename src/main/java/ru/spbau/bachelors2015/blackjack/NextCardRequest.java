package ru.spbau.bachelors2015.blackjack;

import org.jetbrains.annotations.NotNull;

public class NextCardRequest implements Request {
    @Override
    public Object performOn(@NotNull Game game, int playerId) {
        return game.nextCard(playerId);
    }
}
