package org.franket.aoc2022.day8;

import org.franket.helpers.ListHelper;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public record DataGrid<T>(List<List<T>> rows) {

    public DataGrid() {
        this(Collections.emptyList());
    }

    public List<DataGridRecord<T>> records() {
        return IntStream.range(0, rows.size())
                .mapToObj(i ->
                        IntStream.range(0, rows.get(i).size())
                                .mapToObj(j -> createDataGridRecord(j, i))
                ).flatMap(Function.identity())
                .toList();
    }

    public DataGrid<T> appendRow(List<T> row) {
        if (!rows.isEmpty() && rows.get(0).size() != row.size()) {
            throw new IllegalArgumentException("Supplied row does not have the right number of columns");
        }

        return new DataGrid<>(ListHelper.append(rows, row));
    }

    private T recordAt(int x, int y) {
        if (rows.size() < (y - 1)) throw new IndexOutOfBoundsException("There is no row at y=" + y);

        return getColumnInRow(x, rows.get(y));
    }

    private T getColumnInRow(int x, List<T> row) {
        if (row.size() < (x - 1)) throw new IndexOutOfBoundsException("There is no column at x=" + x);

        return row.get(x);
    }

    private List<T> getLeft(int x, int y) {
        var row = rows.get(y);

        return IntStream.range(0, x)
                .mapToObj(row::get)
                .toList();
    }

    private List<T> getRight(int x, int y) {
        var row = rows.get(y);

        return IntStream.range(x + 1, row.size())
                .mapToObj(row::get)
                .toList();
    }

    private List<T> getTop(int x, int y) {
        return IntStream.range(0, y)
                .mapToObj(i -> rows.get(i).get(x))
                .toList();
    }

    private List<T> getBottom(int x, int y) {
        return IntStream.range(y + 1, rows.size())
                .mapToObj(i -> rows.get(i).get(x))
                .toList();
    }

    private DataGridRecord<T> createDataGridRecord(int x, int y) {
        return new DataGridRecord<>(recordAt(x, y), getLeft(x, y), getRight(x, y), getTop(x, y), getBottom(x, y));
    }
}
