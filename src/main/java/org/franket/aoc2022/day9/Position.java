package org.franket.aoc2022.day9;

public record Position(int x, int y) {
    public Position moveHorizontal(int numberOfSteps) {
        return new Position(x + numberOfSteps, y);
    }

    public Position moveVertical(int numberOfSteps) {
        return new Position(x, y + numberOfSteps);
    }

    public Position move(int numberOfStepsHorizontal, int numberOfStepsVertical) {
        return new Position(x + numberOfStepsHorizontal, y + numberOfStepsVertical);
    }
}
