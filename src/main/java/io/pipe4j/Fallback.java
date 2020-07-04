package io.pipe4j;

@FunctionalInterface
public interface Fallback<R> {

    R apply(Throwable t);
}
