package org.franket.aoc2022.day9;

import org.franket.helpers.CollectionsHelper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static org.franket.helpers.StreamHelper.parallelNotAllowed;

public record Rope(Knot head, List<Knot> tail) {

    public static Rope of(int numberOfKnots) {
        var startCoordinate = 0;
        var startPosition = new Position(startCoordinate, startCoordinate);
        var startKnot = new Knot(startPosition, Set.of(startPosition));
        var knots = IntStream.range(0, numberOfKnots - 1)
                .mapToObj(i -> startKnot)
                .toList();

        return new Rope(startKnot, knots);
    }

    public Rope apply(Move move) {
        return IntStream.range(0, move.numberOfSteps())
                .boxed()
                .reduce(this,
                        (acc, elem) -> acc.moveOne(move.direction()),
                        parallelNotAllowed());
    }

    public Rope moveOne(Direction direction) {
        var newHead = moveOne(direction, head);
        var newTail = moveTail(tail, newHead, Collections.emptyList());

        return new Rope(newHead, newTail);
    }

    private Knot moveOne(Direction direction, Knot knot) {
        var numberOfMoves = 1;

        var position = switch (direction) {
            case UP -> knot.position().moveVertical(numberOfMoves);
            case DOWN -> knot.position().moveVertical(-numberOfMoves);
            case LEFT -> knot.position().moveHorizontal(-numberOfMoves);
            case RIGHT -> knot.position().moveHorizontal(numberOfMoves);
        };

        return new Knot(position, CollectionsHelper.add(knot.visitedPositions(), position));
    }

    private List<Knot> moveTail(List<Knot> remainder, Knot previous, List<Knot> acc) {
        if (remainder.isEmpty()) return acc;

        var next = remainder.get(0);
        var newRemainder = remainder.subList(1, remainder.size());

        var newNextPosition = determineNextPosition(next, previous);

        var newNext = new Knot(newNextPosition, CollectionsHelper.add(next.visitedPositions(), newNextPosition));

        return moveTail(newRemainder, newNext, CollectionsHelper.append(acc, newNext));
    }

    private Position determineNextPosition(Knot next, Knot previous) {
        if (shouldMoveHorizontalOnly(next, previous)) {
            int steps = numberOfMoves(previous.position().x(), next.position().x());
            return next.position()
                    .moveHorizontal(steps);
        }

        if (shouldMoveVerticalOnly(next, previous)) {
            int steps = numberOfMoves(previous.position().y(), next.position().y());
            return next.position()
                    .moveVertical(steps);
        }

        if (shouldMoveDiagonal(next, previous)) {
            int xSteps = Integer.compare(previous.position().x(), next.position().x());
            int ySteps = Integer.compare(previous.position().y(), next.position().y());
            return next.position()
                    .move(xSteps, ySteps);
        }

        return next.position();
    }

    private boolean shouldMoveHorizontalOnly(Knot knot, Knot previous) {
        return shouldMoveHorizontal(knot, previous) &&
                knot.position().y() == previous.position().y();
    }

    private boolean shouldMoveVerticalOnly(Knot knot, Knot previous) {
        return shouldMoveVertical(knot, previous) &&
                knot.position().x() == previous.position().x();
    }

    private boolean shouldMoveDiagonal(Knot knot, Knot previous) {
        return !shouldMoveVerticalOnly(knot, previous) && !shouldMoveHorizontalOnly(knot, previous) &&
                (shouldMoveVertical(knot, previous) || shouldMoveHorizontal(knot, previous));
    }

    private boolean shouldMoveHorizontal(Knot knot, Knot previous) {
        return Math.abs(knot.position().x() - previous.position().x()) > 1;
    }

    private boolean shouldMoveVertical(Knot knot, Knot previous) {
        return Math.abs(knot.position().y() - previous.position().y()) > 1;
    }


    private int numberOfMoves(int headPosition, int tailPosition) {
        return Integer.compare(headPosition, tailPosition);
    }
}
