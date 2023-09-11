package com.trendyol.shipment;

public enum ShipmentSize {

    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;

    public static ShipmentSize getUpperLevel(ShipmentSize s) {
        return s != X_LARGE ? ShipmentSize.values()[s.ordinal() + 1] : ShipmentSize.values()[ShipmentSize.values().length - 1];
    }
}
