package org.franket.aoc2022.day9;

import java.util.Arrays;

public enum Direction {
    UP("U"),
    DOWN("D"),
    LEFT("L"),
    RIGHT("R");

    private final String code;

    Direction(String code) {
        this.code = code;
    }

    public static Direction byCode(String code) {
        return Arrays.stream(values())
                .filter(v -> v.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid code supplied: " + code));
    }
}
