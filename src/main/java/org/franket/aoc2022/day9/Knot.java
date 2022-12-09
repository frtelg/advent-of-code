package org.franket.aoc2022.day9;

import java.util.Set;

public record Knot(Position position, Set<Position> visitedPositions) {}
