package io.pipe4j.mock;

import java.io.IOException;

public class DatabaseException extends IOException {

    public DatabaseException() {
        super();
    }

    public DatabaseException(String msg) {
        super(msg);
    }
}
