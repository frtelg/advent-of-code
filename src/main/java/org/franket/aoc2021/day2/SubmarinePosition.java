package org.franket.aoc2021.day2;

record SubmarinePosition(int horizontalPosition, int depth, int aim) {

    public SubmarinePosition(int horizontalPosition, int depth) {
        this(horizontalPosition, depth, 0);
    }

    SubmarinePosition move(SubmarinePositionChange move) {
        return move.apply(this);
    }
}
