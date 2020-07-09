package io.pipe4j.mock;

import java.util.Arrays;
import java.util.List;

public class Database {

    private static final List<Product> DB = Arrays.asList(
        new Product(1, "Product 1", "Very nice product", 10.0),
        new Product(2, "Product 2", "Very nice product 2", 20.0),
        new Product(3, "Product 3", "Very nice product 3", 30.0)
    );

    public List<Product> findAll() {
        return DB;
    }

    public Product findById(int id) {
        return DB.stream().filter(p -> p.getId() == id).findFirst().get();
    }

    public Product findByIdBad(int id) throws DatabaseException {
        throw new DatabaseException("Database is out");
    }
}
