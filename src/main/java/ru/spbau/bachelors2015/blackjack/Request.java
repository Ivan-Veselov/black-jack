package ru.spbau.bachelors2015.blackjack;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

interface Request extends Serializable {
    Object performOn(@NotNull Game game, int playerId);
}
