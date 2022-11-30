package org.franket.aoc2021.day2;

import org.franket.helpers.FileHelper;

public class Day2AoC2021 {
    public static int part1(String fileName) {
        var submarineEndPosition = FileHelper.readLines(fileName)
                .map(l -> parseLine(l, false))
                .reduce(new SubmarinePosition(0, 0, 0), SubmarinePosition::move, (left, right) -> left);

        return submarineEndPosition.depth() * submarineEndPosition.horizontalPosition();
    }

    public static int part2(String fileName) {
        var submarineEndPosition = FileHelper.readLines(fileName)
                .map(l -> parseLine(l, true))
                .reduce(new SubmarinePosition(0, 0, 0), SubmarinePosition::move, (left, right) -> left);

        return submarineEndPosition.depth() * submarineEndPosition.horizontalPosition();
    }

    private static SubmarinePositionChange parseLine(String line, boolean useAim) {
        var splitLine = line.split("\\s+");

        if (splitLine.length < 2) throw new IllegalStateException("Invalid line: " + line);

        var positionChangeType = splitLine[0];
        var positionChangeAmount = Integer.parseInt(splitLine[1]);

        return useAim ? toSubmarinePositionChangeWithAim(positionChangeType, positionChangeAmount) :
                toSubmarinePositionChange(positionChangeType, positionChangeAmount);
    }

    private static SubmarinePositionChange toSubmarinePositionChange(String type, int amount) {
        return switch (type) {
            case "forward" -> new SubmarinePositionChange.Forward(amount);
            case "up" -> new SubmarinePositionChange.Up(amount);
            case "down" -> new SubmarinePositionChange.Down(amount);
            default -> throw new IllegalArgumentException("Cannot parse positionChangeType " + type);
        };
    }

    private static SubmarinePositionChange toSubmarinePositionChangeWithAim(String type, int amount) {
        return switch (type) {
            case "forward" -> new SubmarinePositionChange.ForwardWithAim(amount);
            case "up" -> new SubmarinePositionChange.UpWithAim(amount);
            case "down" -> new SubmarinePositionChange.DownWithAim(amount);
            default -> throw new IllegalArgumentException("Cannot parse positionChangeType " + type);
        };
    }
}

