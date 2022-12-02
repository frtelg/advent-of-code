package org.franket.aoc2022.day2;

import java.util.Arrays;

public enum RockPaperScissorsResult {
    WIN(6, "Z"),
    DRAW(3, "Y"),
    LOSE(0, "X");

    RockPaperScissorsResult(int awardedPoints, String code) {
        this.awardedPoints = awardedPoints;
        this.code = code;
    }

    public static RockPaperScissorsResult fromCode(String code) {
        return Arrays.stream(values())
                .filter(r -> r.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid code: %s", code)));
    }

    final int awardedPoints;
    final String code;

    public int getAwardedPoints() {
        return awardedPoints;
    }
}
