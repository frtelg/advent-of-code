package org.franket.helpers;

import java.util.Arrays;
import java.util.Collections;
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

    public static <T> List<T> reverseOrder(List<T> list) {
        return reverseOrder(list, Collections.emptyList());
    }

    private static <T> List<T> reverseOrder(List<T> rem, List<T> acc) {
        if (rem.isEmpty()) return acc;

        return reverseOrder(rem.subList(1, rem.size()), ListHelper.prepend(acc, rem.get(0)));
    }
}
