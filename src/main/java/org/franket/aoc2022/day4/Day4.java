package org.franket.aoc2022.day4;

import org.franket.aoc2022.AoCPuzzle;
import org.franket.helpers.FileHelper;

import java.util.List;
import java.util.stream.IntStream;

public class Day4 implements AoCPuzzle<Integer> {
    private final String inputFileLocation;

    public Day4(String inputFileLocation) {
        this.inputFileLocation = inputFileLocation;
    }

    @Override
    public Integer solvePart1() {
        return (int) FileHelper.readLines(inputFileLocation)
                .map(this::toAssignmentPair)
                .filter(AssignmentPair::hasFullOverlap)
                .count();
    }

    @Override
    public Integer solvePart2() {
        return (int) FileHelper.readLines(inputFileLocation)
                .map(this::toAssignmentPair)
                .filter(AssignmentPair::hasPartialOverlap)
                .count();
    }

    private AssignmentPair toAssignmentPair(String fileLine) {
        var assignments = fileLine.split(",");

        if (assignments.length != 2) throw new IllegalArgumentException("Cannot parse assignment " + fileLine);

        return new AssignmentPair(toFullAssignment(assignments[0]), toFullAssignment(assignments[1]));
    }

    private List<Integer> toFullAssignment(String assignment) {
        var range = assignment.split("-");

        if (range.length != 2) throw new IllegalArgumentException("Cannot parse assignment " + assignment);

        int from = Integer.parseInt(range[0]);
        int to = Integer.parseInt(range[1]);

        return IntStream.rangeClosed(from, to)
                .boxed()
                .toList();
    }

    @SuppressWarnings("SlowListContainsAll")
    record AssignmentPair(List<Integer> section1, List<Integer> section2) {
        boolean hasFullOverlap() {
            return section1.containsAll(section2) || section2.containsAll(section1);
        }

        boolean hasPartialOverlap() {
            return section1.stream()
                    .anyMatch(section2::contains);
        }
    }
}
