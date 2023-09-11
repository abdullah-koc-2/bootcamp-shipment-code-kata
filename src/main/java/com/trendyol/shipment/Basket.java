package com.trendyol.shipment;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {

    private List<Product> products;

    public ShipmentSize getShipmentSize() {
        ShipmentSize resultSize = ShipmentSize.SMALL;
        boolean doesHaveThree = false;
        Map<ShipmentSize, Integer> productSizes = getProductSizes();

        for (ShipmentSize s : productSizes.keySet())
        {
            if(productSizes.get(s) >= 3) {
                doesHaveThree = true;
                resultSize = ShipmentSize.getUpperLevel(s).ordinal() > resultSize.ordinal() ? ShipmentSize.getUpperLevel(s) : resultSize;
            }
        }
        if(!doesHaveThree) {
            return productSizes.keySet().stream().max(Comparator.comparing(Enum::ordinal)).orElse(ShipmentSize.SMALL);
        }
        return resultSize;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    private Map<ShipmentSize, Integer> getProductSizes() {
        Map<ShipmentSize, Integer> result = new HashMap<>();
        products.forEach(product -> {
            if(result.containsKey(product.getSize())) {
                result.put(product.getSize(), result.get(product.getSize()) + 1);
            }
            else {
                result.put(product.getSize(), 1);
            }
        });
        return result;
    }
}
