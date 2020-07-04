package io.pipe4j;

public class Test {

    public static void main(String[] args) {
        new Test().run();
    }

    public void run() {
        Pipe4j<String, Integer> a = (x) -> Integer.valueOf(x);
        Integer y = a.pipe((x) -> x.doubleValue())
            .pipe((x) -> x + 1.0)
            .pipe((x) -> x.intValue())
            .apply("20");

        System.out.println("result: " + y);
    }
}
