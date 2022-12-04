package org.franket.aoc2022.day3;

import org.franket.aoc2022.AoCPuzzle;
import org.franket.helpers.FileHelper;
import org.franket.helpers.ListHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 implements AoCPuzzle {

    private final String inputFileLocation;

    public Day3(String inputFileLocation) {
        this.inputFileLocation = inputFileLocation;
    }

    @Override
    public int solvePart1() {
        return FileHelper.readLines(inputFileLocation)
                .map(this::toRucksack)
                .map(this::findMatchingChars)
                .mapToInt(this::calculateScore)
                .sum();
    }

    @Override
    public int solvePart2() {
        var fileLines = FileHelper.readLines(inputFileLocation)
                .toList();
        var ruckSacksPer3Elves = partitionPer3(fileLines);

        return ruckSacksPer3Elves.stream()
                .map(this::findMatchingChars)
                .mapToInt(this::calculateScore)
                .sum();
    }

    private Rucksack toRucksack(String fileLine) {
        var pivot = (fileLine.length() / 2);

        return new Rucksack(
                fileLine.substring(0, pivot),
                fileLine.substring(pivot)
        );
    }

    private Set<Character> findMatchingChars(Rucksack rucksack) {
        return findMatchingChars(rucksack.leftCompartmentContent(), rucksack.rightCompartmentContent());
    }

    private Set<Character> findMatchingChars(String left, String right) {
        var leftStream = Arrays.stream(toArray(left));
        var rightList = Arrays.asList(toArray(right));

        return leftStream
                .filter(rightList::contains)
                .flatMap(this::toStream)
                .collect(Collectors.toSet());
    }

    private String getMatchingCharactersAsString(String left, String right) {
        return findMatchingChars(left, right)
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private Set<Character> findMatchingChars(List<String> ruckSacks) {
        return ruckSacks.stream()
                .reduce(this::getMatchingCharactersAsString)
                .stream()
                .flatMap(this::toStream)
                .collect(Collectors.toSet());
    }

    private int calculateScore(Set<Character> characters) {
        return characters.stream()
                .mapToInt(this::calculateScore)
                .sum();
    }

    private String[] toArray(String string) {
        return string.split("");
    }

    private Stream<Character> toStream(String string) {
        return Arrays.stream(toArray(string))
                .map(s -> s.toCharArray()[0]);
    }

    private int calculateScore(Character character) {
        return Character.isUpperCase(character) ?
                (int) character - 38 :
                (int) character - 96;
    }

    private List<List<String>> partitionPer3(List<String> listOfStrings) {
        return partitionPer3(listOfStrings, Collections.emptyList(), Collections.emptyList());
    }

    private List<List<String>> partitionPer3(List<String> remaining, List<List<String>> acc, List<String> currentPartition) {
        if (remaining.isEmpty()) {
            if (currentPartition.isEmpty()) return acc;
            return ListHelper.append(acc, currentPartition);
        }

        var currentElem = remaining.get(0);
        boolean isPartitionFull = currentPartition.size() >= 3;
        var nextPartition = isPartitionFull ?
                List.of(currentElem) :
                ListHelper.append(currentPartition, currentElem);
        var nextAcc = isPartitionFull ?
                ListHelper.append(acc, currentPartition) :
                acc;

        return partitionPer3(remaining.subList(1, remaining.size()), nextAcc, nextPartition);
    }
}
