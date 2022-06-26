package com.selimsahin.garage.model;

public enum VehicleType {
    CAR(1, "Car"), JEEP(2, "Jeep"), TRUCK(4, "Truck");

    private final Integer value;
    private final String text;

    VehicleType(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
