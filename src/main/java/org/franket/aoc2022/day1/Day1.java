package org.franket.aoc2022.day1;

import org.franket.aoc2022.AoCPuzzle;
import org.franket.helpers.FileHelper;
import org.franket.helpers.ListHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Day1 implements AoCPuzzle<Integer> {

    private final String inputFileLocation;

    public Day1(String inputFileLocation) {
        this.inputFileLocation = inputFileLocation;
    }

    @Override
    public Integer solvePart1() {
        var caloriesPerElf = getCaloriesPerElfFromInputFile();

        return toIntStream(caloriesPerElf)
                .max()
                .orElse(0);
    }

    @Override
    public Integer solvePart2() {
        var caloriesPerElf = getCaloriesPerElfFromInputFile();

        return caloriesPerElf.stream()
                .sorted(Comparator.reverseOrder())
                .mapToInt(i -> i)
                .limit(3)
                .sum();
    }

    private List<Integer> getCaloriesPerElfFromInputFile() {
        var fileLines = FileHelper.readLines(inputFileLocation)
                .toList();

        return getCaloriesPerElf(fileLines);
    }

    private List<Integer> getCaloriesPerElf(List<String> fileLines) {
        return getCaloriesPerElf(fileLines, Collections.emptyList(), 0);
    }

    private List<Integer> getCaloriesPerElf(List<String> remainingFileLines, List<Integer> caloriesPerElfCollector, int caloriesForCurrentElf) {
        if (remainingFileLines.isEmpty()) {
            if (caloriesForCurrentElf > 0) return ListHelper.append(caloriesPerElfCollector, caloriesForCurrentElf);
            return caloriesPerElfCollector;
        }

        var currentElement = remainingFileLines.get(0);
        var nextRemaining = remainingFileLines.subList(1, remainingFileLines.size());

        if (currentElement.isBlank()) {
            return getCaloriesPerElf(nextRemaining, ListHelper.append(caloriesPerElfCollector, caloriesForCurrentElf), 0);
        }

        var newCaloriesForCurrentElf = Integer.parseInt(currentElement) + caloriesForCurrentElf;
        return getCaloriesPerElf(nextRemaining, caloriesPerElfCollector, newCaloriesForCurrentElf);
    }

    private IntStream toIntStream(List<Integer> caloriesPerElf) {
        return caloriesPerElf.stream()
                .mapToInt(i -> i);
    }
}
