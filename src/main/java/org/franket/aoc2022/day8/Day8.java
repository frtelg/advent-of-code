package org.franket.aoc2022.day8;

import org.franket.aoc2022.AoCPuzzle;
import org.franket.helpers.FileHelper;

import java.util.Arrays;
import java.util.List;

import static org.franket.helpers.StreamHelper.parallelNotAllowed;

public class Day8 implements AoCPuzzle<Integer> {

    private final String inputFileLocation;

    public Day8(String inputFileLocation) {
        this.inputFileLocation = inputFileLocation;
    }

    @Override
    public Integer solvePart1() {
        var dataGrid = FileHelper.readLines(inputFileLocation)
                .map(this::parseRow)
                .reduce(new DataGrid<Integer>(),
                        DataGrid::appendRow,
                        parallelNotAllowed()
                );

        var forest = new Forest(dataGrid);

        return forest.getTreesVisibleFromOutside()
                .size();
    }

    @Override
    public Integer solvePart2() {
        var dataGrid = FileHelper.readLines(inputFileLocation)
                .map(this::parseRow)
                .reduce(new DataGrid<Integer>(),
                        DataGrid::appendRow,
                        parallelNotAllowed()
                );

        var forest = new Forest(dataGrid);

        return forest.getHighestScenicScore();
    }

    private List<Integer> parseRow(String fileLine) {
        return Arrays.stream(fileLine.split(""))
                .map(Integer::parseInt)
                .toList();
    }
}
