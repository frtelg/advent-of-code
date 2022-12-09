package org.franket.aoc2022.day9;

import org.franket.aoc2022.AoCPuzzle;
import org.franket.helpers.FileHelper;

import static org.franket.helpers.StreamHelper.parallelNotAllowed;

public class Day9 implements AoCPuzzle<Integer> {

    private final String inputFileLocation;

    public Day9(String inputFileLocation) {
        this.inputFileLocation = inputFileLocation;
    }

    @Override
    public Integer solvePart1() {
        return getNumberOfPositionsForTail(2);
    }

    @Override
    public Integer solvePart2() {
        return getNumberOfPositionsForTail(10);
    }

    private Move toMove(String fileLine) {
        var directionAndSteps = fileLine.split("\s+");
        var direction = Direction.byCode(directionAndSteps[0]);
        var numberOfSteps = Integer.parseInt(directionAndSteps[1]);

        return new Move(direction, numberOfSteps);
    }

    private int getNumberOfPositionsForTail(int numberOfKnots) {
        var ropeBridgeStartSituation = Rope.of(numberOfKnots);

        return FileHelper.readLines(inputFileLocation)
                .map(this::toMove)
                .reduce(ropeBridgeStartSituation,
                        Rope::apply,
                        parallelNotAllowed())
                .tail()
                .get(numberOfKnots - 2)
                .visitedPositions()
                .size();
    }
}
