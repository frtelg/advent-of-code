package org.franket.aoc2022.day7;

public record FileSystemItem(FileSystemItemType type, String name, String parentName, int size) {
}
