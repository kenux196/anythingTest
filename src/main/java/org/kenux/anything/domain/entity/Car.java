package org.kenux.anything.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kenux.anything.domain.entity.enums.CarType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Car {

    private String make;
    private int numberOfSeats;
    private CarType type;
}