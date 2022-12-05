package org.franket.aoc2022.day5;

import org.franket.aoc2022.AoCPuzzle;
import org.franket.helpers.FileHelper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.franket.helpers.StreamHelper.parallelNotAllowed;

public class Day5 implements AoCPuzzle<String> {

    private final String inputFileLocation;

    public Day5(String inputFileLocation) {
        this.inputFileLocation = inputFileLocation;
    }

    @Override
    public String solvePart1() {
        var input = parseInput();

        var result = input.commands
                .stream()
                .reduce(input.loadingDock,
                        LoadingDock::apply,
                        parallelNotAllowed());

        return result.getTopCrates();
    }

    @Override
    public String solvePart2() {
        var input = parseInput();

        var result = input.commands
                .stream()
                .reduce(input.loadingDock,
                        LoadingDock::applyAtOnce,
                        parallelNotAllowed());

        return result.getTopCrates();
    }

    private Day5PuzzleInput parseInput() {
        var inputFileParts = FileHelper.readLines(inputFileLocation)
                .collect(Collectors.partitioningBy(s -> s.startsWith("move")));

        var loadingDockPart = inputFileParts.get(false)
                .stream()
                .filter(s -> s.contains("["))
                .toList();
        var loadingDock = parseLoadingDock(loadingDockPart);
        var craneCommands = inputFileParts.get(true)
                .stream()
                .map(this::parseCraneCommand)
                .toList();

        return new Day5PuzzleInput(loadingDock, craneCommands);
    }

    private LoadingDock parseLoadingDock(List<String> input) {
        return input.stream()
                .map(this::divideByColumns)
                .reduce(new LoadingDock(), this::addCratesToStacks, parallelNotAllowed());
    }

    private List<String> divideByColumns(String fileLine) {
        int columnLength = 3;
        int separatorLength = 1;
        int columnLengthPlusSeparator = columnLength + separatorLength;
        String fileLineWithFinalColumnSeparatorAdded = fileLine + " ";

        return IntStream.rangeClosed(0, (fileLineWithFinalColumnSeparatorAdded.length() / columnLengthPlusSeparator) - 1)
                .mapToObj(i -> {
                    var startIndex = i * columnLengthPlusSeparator;
                    var endIndex = startIndex + columnLengthPlusSeparator;

                    return fileLineWithFinalColumnSeparatorAdded.substring(startIndex, endIndex);
                })
                .toList();
    }

    private LoadingDock addCratesToStacks(LoadingDock accumulator, List<String> crates) {
        return IntStream.range(0, crates.size())
                .filter(i -> !crates.get(i).trim().isBlank())
                .boxed()
                .reduce(accumulator,
                        (acc, elem) -> {
                            var parsedElem = crates.get(elem)
                                    .trim()
                                    .replace("[", "")
                                    .replace("]", "");
                            return acc.addToBottom(parsedElem, elem + 1);
                        },
                        parallelNotAllowed()
                );
    }

    private CraneCommand parseCraneCommand(String fileLine) {
        var splitAtFrom = fileLine.split("from");
        var splitAtTo = splitAtFrom[1].split("to");
        int numberOfCratesToMove = Integer.parseInt(
                splitAtFrom[0].replace("move", "").trim()
        );
        int from = Integer.parseInt(splitAtTo[0].trim());
        int to = Integer.parseInt(splitAtTo[1].trim());

        return new CraneCommand(numberOfCratesToMove, from, to);
    }

    record Day5PuzzleInput(LoadingDock loadingDock, List<CraneCommand> commands) {}
}
