package org.franket.aoc2021.day1;

import org.franket.helpers.FileHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Day1AoC2021 {
    public static int part1(String fileName) {
        var listOfInts = FileHelper.readLines(fileName)
                .map(Integer::parseInt)
                .toList();

        return countNumberOfIncreases(listOfInts);
    }

    public static int part2(String fileName) {
        var listOfInts = FileHelper.readLines(fileName)
                .map(Integer::parseInt)
                .toList();

        List<List<Integer>> collector = IntStream.range(0, listOfInts.size())
                .mapToObj(i -> {
                    if (listOfInts.size() >= i + 3) {
                        return listOfInts.subList(i, i + 3);
                    } else {
                        return listOfInts.subList(i, listOfInts.size());
                    }
                }).toList();

        var listOfSums = collector.stream()
                .map(i ->
                        i.stream()
                                .mapToInt(Integer::intValue)
                                .sum()
                )
                .toList();

        return countNumberOfIncreases(listOfSums);
    }

    private static int countNumberOfIncreases(List<Integer> listOfInts) {
        return countNumberOfIncreases(listOfInts, Optional.empty(), 0);
    }

    private static int countNumberOfIncreases(List<Integer> remaining, Optional<Integer> previousOpt, int countOfIncreases) {
        if (remaining.isEmpty()) return countOfIncreases;

        var newRemaining = remaining.subList(1, remaining.size());
        var current = remaining.get(0);
        var newPrevious = Optional.of(current);

        if (previousOpt.isEmpty()) return countNumberOfIncreases(newRemaining, newPrevious, 0);

        int previous = previousOpt.get();
        int newCountOfIncreases = previous < current ? countOfIncreases + 1 : countOfIncreases;

        return countNumberOfIncreases(newRemaining, newPrevious, newCountOfIncreases);
    }
}
