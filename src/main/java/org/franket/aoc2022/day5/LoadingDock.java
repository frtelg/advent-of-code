package org.franket.aoc2022.day5;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.franket.helpers.StreamHelper.parallelNotAllowed;

public record LoadingDock(Map<Integer, String> stacks) {

    public LoadingDock() {
        this(Collections.emptyMap());
    }

    public String getTopCrates() {
        return stacks.values()
                .stream()
                .map(s -> s.substring(0, 1))
                .collect(Collectors.joining());
    }

    public LoadingDock apply(CraneCommand craneCommand) {
        return move(craneCommand.moveNumberOfCrates(), craneCommand.from(), craneCommand.to());
    }

    public LoadingDock applyAtOnce(CraneCommand craneCommand) {
        return moveAllAtOnce(craneCommand.moveNumberOfCrates(), craneCommand.from(), craneCommand.to());
    }

    public LoadingDock move(int numberOfCrates, int fromStack, int toStack) {
        return IntStream.range(0, numberOfCrates)
                .boxed()
                .reduce(this, (acc, elem) -> acc.move(fromStack, toStack), parallelNotAllowed());
    }

    public LoadingDock moveAllAtOnce(int numberOfCrates, int fromStack, int toStack) {
        var currentFromStack = stacks.get(fromStack);
        var currentToStack = stacks.get(toStack);

        var newFromStack = currentFromStack.substring(numberOfCrates);
        var newToStack = currentFromStack.substring(0, numberOfCrates) + currentToStack;

        return update(Set.of(
                Map.entry(fromStack, newFromStack),
                Map.entry(toStack, newToStack)
        ));
    }

    public LoadingDock move(int fromStack, int toStack) {
        var currentFromStack = stacks.get(fromStack);
        var currentToStack = stacks.get(toStack);

        var newFromStack = currentFromStack.substring(1);
        var newToStack = currentFromStack.charAt(0) + currentToStack;

        return update(Set.of(
                Map.entry(fromStack, newFromStack),
                Map.entry(toStack, newToStack)
        ));
    }

    private LoadingDock update(Set<Map.Entry<Integer, String>> updatedEntries) {
        var newStacks = stacks.entrySet()
                .stream()
                .map(s ->
                    updatedEntries.stream()
                            .filter(e -> e.getKey().equals(s.getKey()))
                            .findFirst()
                            .orElse(s)
                ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return new LoadingDock(newStacks);
    }

    public LoadingDock addToBottom(String crateName, int stack) {
        var currentValue = stacks.getOrDefault(stack, "");
        var newValue = currentValue + crateName;

        var newStacks = stacks.containsKey(stack) ?
                replaceStack(stack, newValue) :
                addStack(stack, newValue);

        return new LoadingDock(newStacks);
    }

    private Map<Integer, String> replaceStack(int key, String value) {
        return stacks.entrySet()
                .stream()
                .map(s -> {
                    if (s.getKey().equals(key)) return Map.entry(s.getKey(), value);
                    return s;
                }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<Integer, String> addStack(int key, String value) {
        return Stream.concat(stacks.entrySet().stream(), Stream.of(Map.entry(key, value)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
