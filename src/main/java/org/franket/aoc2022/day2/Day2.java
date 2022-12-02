package org.franket.aoc2022.day2;

import org.franket.aoc2022.AoCPuzzle;
import org.franket.helpers.FileHelper;

public class Day2 implements AoCPuzzle {

    private final String inputFileLocation;

    public Day2(String inputFileLocation) {
        this.inputFileLocation = inputFileLocation;
    }

    @Override
    public int solvePart1() {
        return FileHelper.readLines(inputFileLocation)
                .map(this::toContest)
                .mapToInt(this::getFullContestScore)
                .sum();
    }

    @Override
    public int solvePart2() {
        return FileHelper.readLines(inputFileLocation)
                .map(this::toContestWithDesiredOutcome)
                .mapToInt(this::getFullContestScore)
                .sum();
    }

    private RockPaperScissorsContest toContest(String line) {
        var choiceCodes = line.split("\\s+");

        if (choiceCodes.length < 2) throw new IllegalArgumentException("Invalid line, should have two parameters");

        var myChoice = choiceCodes[1];
        var opponentsChoice = choiceCodes[0];

        return new RockPaperScissorsContest(
                RockPaperScissorsChoice.fromCode(myChoice),
                RockPaperScissorsChoice.fromCode(opponentsChoice)
        );
    }

    private RockPaperScissorsContest toContestWithDesiredOutcome(String line) {
        var choiceCodes = line.split("\\s+");

        if (choiceCodes.length < 2) throw new IllegalArgumentException("Invalid line, should have two parameters");

        var opponentsChoice = RockPaperScissorsChoice.fromCode(choiceCodes[0]);
        var desiredOutcome = RockPaperScissorsResult.fromCode(choiceCodes[1]);

        return new RockPaperScissorsContest(
                opponentsChoice.determineOpponentChoice(desiredOutcome),
                opponentsChoice
        );
    }

    private int getFullContestScore(RockPaperScissorsContest contest) {
        var contestResult = contest.getResult();

        return contestResult.getAwardedPoints() + contest.getMyChoiceValue();
    }
}
