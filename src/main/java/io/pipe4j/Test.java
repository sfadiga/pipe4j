package io.pipe4j;

import java.io.IOException;

import static io.pipe4j.SilentThrow.silent;

public class Test {

    public static void main(String[] args) {
        new Test().run();
    }

    public void run() {
        Pipe4j<String, Integer> a = (x) -> Integer.valueOf(x);

        Integer y = a.pipe((x) -> x.doubleValue())
            .pipe((x) -> x + 1.0)
//            .pipe((x) -> x.intValue())
            .pipe((x) -> silent(() -> toInt(x)))
            .apply("20");

        System.out.println("result: " + y);

    }

    public Integer toInt(Double value) throws IOException {
        throw new IOException();
    }

}
