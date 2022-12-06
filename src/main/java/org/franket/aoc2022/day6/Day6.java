package org.franket.aoc2022.day6;

import org.franket.aoc2022.AoCPuzzle;
import org.franket.helpers.FileHelper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.IntStream;

public class Day6 implements AoCPuzzle<Integer> {

    private final String inputFileLocation;

    public Day6(String inputFileLocation) {
        this.inputFileLocation = inputFileLocation;
    }

    @Override
    public Integer solvePart1() {
        return FileHelper.readLines(inputFileLocation)
                .findFirst()
                .map(l -> getFirstMarkerPosition(l, 4))
                .orElse(0);
    }

    @Override
    public Integer solvePart2() {
        return FileHelper.readLines(inputFileLocation)
                .findFirst()
                .map(l -> getFirstMarkerPosition(l, 14))
                .orElse(0);
    }

    private int getFirstMarkerPosition(String input, int numberOfUniqueCharactersRequired) {
        var characterList = Arrays.asList(input.split(""));

        return IntStream.range(0, characterList.size())
                .filter(i -> {
                    var startIndex = i < (numberOfUniqueCharactersRequired - 1) ? 0 : i - (numberOfUniqueCharactersRequired - 1);
                    var endIndexExcl = i + 1;

                    var fourMostRecentChars = characterList.subList(startIndex, endIndexExcl);

                    return new HashSet<>(fourMostRecentChars)
                            .size() == numberOfUniqueCharactersRequired;
                })
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No first marker position could be found for " + input))
                + 1; // non-indexed value used
    }
}
