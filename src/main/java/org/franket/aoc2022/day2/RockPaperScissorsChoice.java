package org.franket.aoc2022.day2;

import java.util.Arrays;
import java.util.Set;

public enum RockPaperScissorsChoice {
    ROCK(1, "A", "X"),
    PAPER(2, "B", "Y"),
    SCISSORS(3, "C", "Z");

    RockPaperScissorsChoice(int value, String... codes) {
        this.value = value;
        this.codes = Set.of(codes);
    }

    public static RockPaperScissorsChoice fromCode(String code) {
        return Arrays.stream(RockPaperScissorsChoice.values())
                .filter(r -> r.codes.contains(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid code %s", code)));
    }

    final int value;
    final Set<String> codes;

    public RockPaperScissorsResult battle(RockPaperScissorsChoice opponentsChoice) {
        if (winsFrom() == opponentsChoice) return RockPaperScissorsResult.WIN;
        if (losesTo() == opponentsChoice) return RockPaperScissorsResult.LOSE;
        return RockPaperScissorsResult.DRAW;
    }

    public RockPaperScissorsChoice winsFrom() {
        return values()[(ordinal() + 2) % 3];
    }

    public RockPaperScissorsChoice losesTo() {
        return values()[(ordinal() + 1) % 3];
    }

    public RockPaperScissorsChoice determineOpponentChoice(RockPaperScissorsResult resultWantedByOpponent) {
        return switch (resultWantedByOpponent) {
            case WIN -> losesTo();
            case LOSE -> winsFrom();
            case DRAW -> this;
        };
    }
}
