package io.pipe4j;

@FunctionalInterface
public interface Pipe4j<T, R> {

    R apply(T var1);

    default <V> Pipe4j<T, V> pipe(Pipe4j<? super R, ? extends V> next) {
        return (T t) -> next.apply(apply(t));
    }
}
