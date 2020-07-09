package io.pipe4j.mock;

import java.util.List;

public class ShipmentService {

    public double calculateShip(Product product) {
        return product.getId() * 10;
    }

    public double calculateShip(List<Product> products) {
        return products.stream().map((p) -> p.getId()).reduce(0, (total, id) -> total + (id * 10));
    }

    public double calculateShipBad(Product product) {
        throw new ShipmentException("Shipment service is out");
    }
}
