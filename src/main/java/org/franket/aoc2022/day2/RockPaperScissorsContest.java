package org.franket.aoc2022.day2;

public record RockPaperScissorsContest(RockPaperScissorsChoice myChoice, RockPaperScissorsChoice opponentChoice) {
    public RockPaperScissorsResult getResult() {
        return myChoice.battle(opponentChoice);
    }

    public int getMyChoiceValue() {
        return myChoice.value;
    }
}
