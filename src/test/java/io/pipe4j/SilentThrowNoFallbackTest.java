package io.pipe4j;

import io.pipe4j.mock.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.pipe4j.SilentThrow.silent;
import static org.junit.jupiter.api.Assertions.*;

public class SilentThrowNoFallbackTest {

    @Test
    public void pipeShouldThrowUncheckedException() {
        Database db = new Database();
        ShipmentService service = new ShipmentService();

        Pipe4j<Integer, Product> pipe = (id) -> db.findById(id);

        ShipmentException e = assertThrows(ShipmentException.class, () -> pipe
            .pipe((p) -> silent(() -> service.calculateShipBad(p)))
            .apply(3));

        assertEquals(e.getMessage(), "Shipment service is out");
    }

    @Test
    public void pipeShouldThrowCheckedException() {
        Database db = new Database();

        Pipe4j<Void, List<Product>> pipe = (v) -> db.findAll();


        DatabaseException e = assertThrows(DatabaseException.class, () -> pipe
            .pipe((ps) -> ps.get(2).getId())
            .pipe((id) -> silent(() -> db.findByIdBad(id)))
            .apply(null));

        assertEquals(e.getMessage(), "Database is out");
    }
}
