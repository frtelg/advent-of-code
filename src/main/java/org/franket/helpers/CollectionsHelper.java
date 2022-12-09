package org.franket.helpers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionsHelper {
    @SafeVarargs
    public static <T> List<T> prepend(List<T> list, T... elements) {
        return Stream.concat(Arrays.stream(elements), list.stream())
                .toList();
    }

    @SafeVarargs
    public static <T> List<T> append(List<T> list, T... elements) {
        return Stream.concat(list.stream(), Arrays.stream(elements))
                .toList();
    }

    @SafeVarargs
    public static <T> Set<T> add(Set<T> list, T... elements) {
        return Stream.concat(list.stream(), Arrays.stream(elements))
                .collect(Collectors.toSet());
    }

    public static <T> T getLastElement(List<T> list) {
        if (list.isEmpty()) throw new NoSuchElementException("Empty collection supplied");

        return list.get(list.size() -1);
    }

    public static <T> List<T> reverseOrder(List<T> list) {
        return reverseOrder(list, Collections.emptyList());
    }

    private static <T> List<T> reverseOrder(List<T> rem, List<T> acc) {
        if (rem.isEmpty()) return acc;

        return reverseOrder(rem.subList(1, rem.size()), CollectionsHelper.prepend(acc, rem.get(0)));
    }
}
