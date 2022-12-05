package org.franket.helpers;

import java.util.function.BinaryOperator;

public class StreamHelper {
    public static <T> BinaryOperator<T> parallelNotAllowed() {
        return (l, r) -> {
            throw new IllegalStateException("Operation not allowed on parallel stream");
        };
    }
}
