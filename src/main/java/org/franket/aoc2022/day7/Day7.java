package org.franket.aoc2022.day7;

import org.franket.aoc2022.AoCPuzzle;
import org.franket.helpers.FileHelper;
import org.franket.helpers.CollectionsHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day7 implements AoCPuzzle<Integer> {

    private final String inputFileLocation;

    public Day7(String inputFileLocation) {
        this.inputFileLocation = inputFileLocation;
    }

    @Override
    public Integer solvePart1() {
        var fileSystem = FileHelper.readLines(inputFileLocation)
                .toList();

        var items = getFileSystemItems(fileSystem, "", Collections.emptyList());

        var directories = getAllDirectoryPaths(items);

        return directories.stream()
                .mapToInt(d -> this.getDirectorySize(d, items))
                .filter(i -> i <= 100000)
                .sum();
    }

    @Override
    public Integer solvePart2() {
        var fileSystem = FileHelper.readLines(inputFileLocation)
                .toList();

        var items = getFileSystemItems(fileSystem, "", Collections.emptyList());

        var totalDiskUse = items.stream()
                .mapToInt(FileSystemItem::size)
                .sum();

        var currentFreeSpace = 70_000_000 - totalDiskUse;
        var requiredFreeSpace = 30_000_000 - currentFreeSpace;

        var directories = getAllDirectoryPaths(items);

        return directories.stream()
                .mapToInt(d -> this.getDirectorySize(d, items))
                .filter(i -> i >= requiredFreeSpace)
                .min()
                .orElse(0);
    }

    private List<FileSystemItem> getFileSystemItems(List<String> remaining, String currentStack, List<FileSystemItem> acc) {
        if (remaining.isEmpty()) return acc;

        var head = remaining.get(0);
        var tail = remaining.subList(1, remaining.size());

        if (isNavigateToDirCommand(head)) {
            return getFileSystemItems(tail, String.format("%s/%s", getUniversalStack(currentStack), getDirectoryNameFromCommand(head)), acc);
        }

        if (isNavigateBackCommand(head)) {
            var currentStackList = Arrays.asList(currentStack.split("/"));
            var newStack = String.join("/", currentStackList.subList(0, currentStackList.size() - 1));

            return getFileSystemItems(tail, newStack, acc);
        }

        if (isLsCommand(head)) {
            return getFileSystemItems(tail, currentStack, acc);
        }

        return getFileSystemItems(tail, currentStack, CollectionsHelper.append(acc, parseFileSystemItem(head, currentStack)));
    }

    private boolean isNavigateToDirCommand(String line) {
        return isCdCommand(line) &&
                !line.endsWith("..");
    }

    private boolean isNavigateBackCommand(String line) {
        return isCdCommand(line) &&
                line.endsWith("..");
    }

    private boolean isCdCommand(String line) {
        return line.startsWith("$") &&
                line.replace("$", "")
                        .trim()
                        .startsWith("cd");
    }

    private boolean isLsCommand(String line) {
        return line.startsWith("$") &&
                line.replace("$", "")
                        .trim()
                        .startsWith("ls");
    }

    private String getDirectoryNameFromCommand(String line) {
        var directoryName = line.replace("$", "")
                .replace(" cd ", "")
                .trim();

        if (directoryName.endsWith("/")) return directoryName.substring(0, directoryName.length() - 1);
        return directoryName;
    }

    private FileSystemItem parseFileSystemItem(String line, String stack) {
        if (line.trim().startsWith("dir")) {
            return parseDir(line, stack);
        }

        return parseFile(line, stack);
    }

    private FileSystemItem parseDir(String line, String stack) {
        var dirName = line.split("\s+")[1].trim();

        return new FileSystemItem(dirName, stack, 0);
    }

    private FileSystemItem parseFile(String line, String stack) {
        var sizeAndName = line.split("\s+");
        var name = sizeAndName[1].trim();
        var size = Integer.parseInt(sizeAndName[0].trim());

        return new FileSystemItem(name, stack, size);
    }

    private String getUniversalStack(String stack) {
        if (stack.endsWith("/")) return stack.substring(0, stack.length() - 1);
        return stack;
    }

    private Set<String> getAllDirectoryPaths(List<FileSystemItem> fileSystem) {
        return fileSystem.stream()
                .map(FileSystemItem::parentName)
                .collect(Collectors.toSet());
    }

    private int getDirectorySize(String path, List<FileSystemItem> fileSystemItems) {
        return fileSystemItems.stream()
                .filter(i -> i.parentName().startsWith(path))
                .mapToInt(FileSystemItem::size)
                .sum();
    }
}
