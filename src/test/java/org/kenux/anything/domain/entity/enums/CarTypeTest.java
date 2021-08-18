package org.kenux.anything.domain.entity.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTypeTest {

    @Test
    void enumTest() {
        final CarType[] values = CarType.values();
        for (CarType value : values) {
            System.out.println("value = " + value + ", name = " + value.getName());
        }
    }

}