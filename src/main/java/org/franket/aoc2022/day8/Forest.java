package org.franket.aoc2022.day8;

import org.franket.helpers.CollectionsHelper;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public record Forest(DataGrid<Integer> trees) {
    public List<DataGridRecord<Integer>> getTreesVisibleFromOutside() {
        return trees.records()
                .stream()
                .filter(this::isVisible)
                .toList();
    }

    public int getHighestScenicScore() {
        return trees.records()
                .stream()
                .mapToInt(this::getScenicScoreForTree)
                .max()
                .orElse(0);
    }

    private int getScenicScoreForTree(DataGridRecord<Integer> tree) {
        var leftTrees = CollectionsHelper.reverseOrder(tree.left());
        var rightTrees = tree.right();
        var topTrees = CollectionsHelper.reverseOrder(tree.top());
        var bottomTrees = tree.bottom();

        return countNumberOfVisibleTrees(tree, leftTrees) *
                countNumberOfVisibleTrees(tree, rightTrees) *
                countNumberOfVisibleTrees(tree, topTrees) *
                countNumberOfVisibleTrees(tree, bottomTrees);
    }

    public boolean isVisible(DataGridRecord<Integer> tree) {
        return tree.left().isEmpty() ||
                tree.right().isEmpty() ||
                tree.bottom().isEmpty() ||
                tree.top().isEmpty() ||
                noNeighbourBlockingVisibility(tree);
    }

    private boolean noNeighbourBlockingVisibility(DataGridRecord<Integer> tree) {
        Predicate<Supplier<List<Integer>>> hasBlockingNeighbour =
                records -> records.get().stream().noneMatch(r -> r >= tree.value());

        return hasBlockingNeighbour.test(tree::left) ||
                hasBlockingNeighbour.test(tree::bottom) ||
                hasBlockingNeighbour.test(tree::right) ||
                hasBlockingNeighbour.test(tree::top);
    }

    private int countNumberOfVisibleTrees(DataGridRecord<Integer> tree, List<Integer> neighbourTrees) {
        boolean isThereMoreToSee = true;
        int numberOfVisibleTrees = 0;
        var iterator = neighbourTrees.iterator();

        while (isThereMoreToSee && iterator.hasNext()) {
            var next = iterator.next();

            if (next >= tree.value()) {
                isThereMoreToSee = false;
            }

            numberOfVisibleTrees++;
        }

        return numberOfVisibleTrees;
    }
}
