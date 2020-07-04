package io.pipe4j;

import java.util.concurrent.Callable;

public class SilentThrow {

    public static <T> T silent(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Throwable e) {
            sneakyThrow(e);
            return null;
        }
    }

    public static <T> T silent(Callable<T> callable, Fallback<T> fallback) {
        try {
            return callable.call();
        } catch (Throwable e) {
            return fallback.apply(e);
        }
    }

    public static <E extends Throwable> void sneakyThrow(Throwable e) throws E {
        throw (E) e;
    }
}
