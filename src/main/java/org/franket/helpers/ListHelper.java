package org.franket.helpers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ListHelper {
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
}
