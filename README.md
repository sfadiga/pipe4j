# Pipe4j

[![Build Status](https://travis-ci.org/igventurelli/pipe4j.svg?branch=master)](https://travis-ci.org/igventurelli/pipe4j)
[![Known Vulnerabilities](https://snyk.io/test/github/igventurelli/pipe4j/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/igventurelli/pipe4j?targetFile=pom.xml)
[![Maintainability](https://api.codeclimate.com/v1/badges/381d36217cd7886630cb/maintainability)](https://codeclimate.com/github/igventurelli/pipe4j/maintainability)

A cleaner way to write Java code, through a pipeline

## Highlights
- The ability to chain all method calls in a single path
- Receive the result of the last operation as the input for the next one
- Don't let Exceptions make your code looks bad
- Even Checked Exceptions 😜
- Very simple and easy to use

## How does it work?

```java
import io.pipe4j.Pipe4j;
import static io.pipe4j.SilentThrow.silent;

public class Main {

    public static void main(String[] args) {
        // Receives an String and returns an Integer
        Pipe4j<String, Integer> pipe = value -> Integer.valueOf(value);

        Integer result = pipe
            .pipe(i -> i.doubleValue()) // in: Integer, out: Double
            .pipe(d -> d + 2.0) // in: Double, out: Double
            // handle checked Exception with no try/catch or by adding "throws" to the method signature
            // and returns a fallback value (99)
            .pipe(d -> silent(() -> toInt(d), (e) -> 99)) // in: Double, out: Integer
            .apply("10"); // the argument for the first step of the pipeline

        System.out.println("Result: " + result); // => Result: 99 (or 12, if toInt() don't throw any exception
    }

    public static Integer toInt(Double value) throws Exception {
        throw new Exception("Something went wrong");
    }
}
```

## Getting started
- [Installation](https://igventurelli.gitbook.io/pipe4j/getting-started/installation)
- [Documentation](https://igventurelli.gitbook.io/pipe4j/getting-started/docs)

## License
[MIT](https://github.com/igventurelli/pipe4j/blob/master/LICENSE.md)
