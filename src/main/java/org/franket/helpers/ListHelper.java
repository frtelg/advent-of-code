package org.franket.helpers;

import java.util.List;
import java.util.stream.Stream;

public class ListHelper {
    public static <T> List<T> addToStart(List<T> list, T element) {
        return Stream.concat(Stream.of(element), list.stream())
                .toList();
    }

    public static <T> List<T> add(List<T> list, T element) {
        return Stream.concat(list.stream(), Stream.of(element))
                .toList();
    }
}
