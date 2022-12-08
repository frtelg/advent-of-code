package org.franket.aoc2022.day8;

import java.util.List;

public record DataGridRecord<T>(T value, List<T> left, List<T> right, List<T> top, List<T> bottom) {}
