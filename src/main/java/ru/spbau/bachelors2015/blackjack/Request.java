package ru.spbau.bachelors2015.blackjack;

import org.jetbrains.annotations.NotNull;

interface Request {
    Object performOn(@NotNull Game game, int playerId);
}
