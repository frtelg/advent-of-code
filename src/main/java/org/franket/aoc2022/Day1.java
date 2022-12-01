package org.franket.aoc2022;

import org.franket.helpers.FileHelper;
import org.franket.helpers.ListHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Day1 {
    public int solvePart1(String fileLocation) {
        var caloriesPerElf = readAndGetCaloriesPerElf(fileLocation);

        return toIntStream(caloriesPerElf)
                .max()
                .orElse(0);
    }

    public int solvePart2(String fileLocation) {
        var caloriesPerElf = readAndGetCaloriesPerElf(fileLocation);

        return caloriesPerElf.stream()
                .sorted(Comparator.reverseOrder())
                .mapToInt(i -> i)
                .limit(3)
                .sum();
    }

    private List<Integer> readAndGetCaloriesPerElf(String fileName) {
        var fileLines = FileHelper.readLines(fileName)
                .toList();

        return getCaloriesPerElf(fileLines);
    }

    private List<Integer> getCaloriesPerElf(List<String> fileLines) {
        return getCaloriesPerElf(fileLines, Collections.emptyList(), 0);
    }

    private List<Integer> getCaloriesPerElf(List<String> remainingFileLines, List<Integer> caloriesPerElfCollector, int caloriesForCurrentElf) {
        if (remainingFileLines.isEmpty()) {
            if (caloriesForCurrentElf > 0) return ListHelper.add(caloriesPerElfCollector, caloriesForCurrentElf);
            return caloriesPerElfCollector;
        }

        var currentElement = remainingFileLines.get(0);
        var nextRemaining = remainingFileLines.subList(1, remainingFileLines.size());

        if (currentElement.isBlank()) {
            return getCaloriesPerElf(nextRemaining, ListHelper.add(caloriesPerElfCollector, caloriesForCurrentElf), 0);
        }

        var newCaloriesForCurrentElf = Integer.parseInt(currentElement) + caloriesForCurrentElf;
        return getCaloriesPerElf(nextRemaining, caloriesPerElfCollector, newCaloriesForCurrentElf);
    }

    private IntStream toIntStream(List<Integer> caloriesPerElf) {
        return caloriesPerElf.stream()
                .mapToInt(i -> i);
    }
}
