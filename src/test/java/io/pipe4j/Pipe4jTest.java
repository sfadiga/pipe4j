package io.pipe4j;

import io.pipe4j.mock.Database;
import io.pipe4j.mock.Product;
import io.pipe4j.mock.ShipmentService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Pipe4jTest {

    @Test
    public void pipeShouldReturn30() {
        Database db = new Database();
        ShipmentService service = new ShipmentService();

        Pipe4j<Integer, Product> pipe = (id) -> db.findById(id);
        Double result = pipe
            .pipe((p) -> service.calculateShip(p))
            .apply(3);

        assertEquals(30.0, result);
    }

    @Test
    public void pipeShouldReturn60() {
        Database db = new Database();
        ShipmentService service = new ShipmentService();

        Pipe4j<Void, List<Product>> pipe = (v) -> db.findAll();
        Double result = pipe
            .pipe((ps) -> service.calculateShip(ps))
            .apply(null);

        assertEquals(60.0, result);
    }
}
