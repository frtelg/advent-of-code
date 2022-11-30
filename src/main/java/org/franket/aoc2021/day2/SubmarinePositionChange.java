package org.franket.aoc2021.day2;

interface SubmarinePositionChange {
    SubmarinePosition apply(SubmarinePosition currentPosition);

    record Down(int steps) implements SubmarinePositionChange {
        @Override
        public SubmarinePosition apply(SubmarinePosition currentPosition) {
            return new SubmarinePosition(currentPosition.horizontalPosition(), currentPosition.depth() + steps);
        }
    }

    record DownWithAim(int steps) implements SubmarinePositionChange {
        @Override
        public SubmarinePosition apply(SubmarinePosition currentPosition) {
            return new SubmarinePosition(currentPosition.horizontalPosition(), currentPosition.depth(), currentPosition.aim() + steps);
        }
    }

    record Forward(int steps) implements SubmarinePositionChange {
        @Override
        public SubmarinePosition apply(SubmarinePosition currentPosition) {
            return new SubmarinePosition(currentPosition.horizontalPosition() + steps, currentPosition.depth());
        }
    }

    record ForwardWithAim(int steps) implements SubmarinePositionChange {
        @Override
        public SubmarinePosition apply(SubmarinePosition currentPosition) {
            return new SubmarinePosition(currentPosition.horizontalPosition() + steps, currentPosition.depth() + (currentPosition.aim() * steps), currentPosition.aim());
        }
    }

    record Up(int steps) implements SubmarinePositionChange {
        @Override
        public SubmarinePosition apply(SubmarinePosition currentPosition) {
            return new SubmarinePosition(currentPosition.horizontalPosition(), currentPosition.depth() - steps);
        }
    }

    record UpWithAim(int steps) implements SubmarinePositionChange {
        @Override
        public SubmarinePosition apply(SubmarinePosition currentPosition) {
            return new SubmarinePosition(currentPosition.horizontalPosition(), currentPosition.depth(), currentPosition.aim() - steps);
        }
    }
}
