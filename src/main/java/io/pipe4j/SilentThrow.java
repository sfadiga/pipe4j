package io.pipe4j;

import java.util.concurrent.Callable;

public class SilentThrow {

    public static <T> T silent(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            sneakyThrow(e);
            return null;
        }
    }

    public static <E extends Throwable> void sneakyThrow(Throwable e) throws E {
        throw (E) e;
    }
}
