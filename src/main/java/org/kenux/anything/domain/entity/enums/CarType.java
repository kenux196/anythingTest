package org.kenux.anything.domain.entity.enums;

public enum CarType {
    Passenger_Car("승용차"),
    Truck("트럭"),
    Pickup_Truck("소형 트럭"),
    Compact_Car("소형차"),
    Sedan("세단"),
    Wagon("웨건");


    private String name;

    CarType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
