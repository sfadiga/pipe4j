package io.pipe4j;

import io.pipe4j.mock.Database;
import io.pipe4j.mock.Product;
import io.pipe4j.mock.ShipmentService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.pipe4j.SilentThrow.silent;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SilentThrowWithFallbackTest {

    @Test
    public void pipeShouldReturnTheFallbackValueForUncheckedException() {
        Database db = new Database();
        ShipmentService service = new ShipmentService();

        Pipe4j<Integer, Product> pipe = (id) -> db.findById(id);
        Double result = pipe
            .pipe((p) -> silent(() -> service.calculateShipBad(p), (e) -> 10.0))
            .apply(3);

        assertEquals(result, 10.0);
    }

    @Test
    public void pipeShouldReturnTheFallbackValueForCheckedException() {
        Database db = new Database();
        ShipmentService service = new ShipmentService();
        Product fallback = new Product(4, "Product 4", "Cool product", 40.0);

        Pipe4j<Void, List<Product>> pipe = (v) -> db.findAll();
        Product result = pipe
            .pipe((ps) -> ps.get(2).getId())
            .pipe((id) -> silent(() -> db.findByIdBad(id), (e) -> fallback))
            .apply(null);


        assertEquals(result.getId(), 4);
    }

    @Test
    public void pipeShouldReturnTheFallbackFunctionForUncheckedException() {
        Database db = new Database();
        ShipmentService service = new ShipmentService();

        Pipe4j<Integer, Product> pipe = (id) -> db.findById(id);
        Double result = pipe
            .pipe((p) -> silent(() -> service.calculateShipBad(p), (e) -> shipmentServiceCalculationFallback(p, e)))
            .apply(3);

        assertEquals(result, 20.0);
    }

    @Test
    public void pipeShouldReturnTheFallbackFunctionForCheckedException() {
        Database db = new Database();

        Pipe4j<Void, List<Product>> pipe = (v) -> db.findAll();
        Product result = pipe
            .pipe((ps) -> ps.get(2).getId())
            .pipe((id) -> silent(() -> db.findByIdBad(id), (e) -> findByIdFallback(e)))
            .apply(null);


        assertEquals(result.getId(), 5);
    }

    private Double shipmentServiceCalculationFallback(Product product, Throwable e) {
        System.out.println("Something is broken: " + e.getMessage());
        /* try another service or any other thing for fallback */
        return 20.0;
    }

    private Product findByIdFallback(Throwable e) {
        System.out.println("Something is broken: " + e.getMessage());
        /* try another service or any other thing for fallback */
        return new Product(5, "Product 5", "Cool product", 50.0);
    }
}
