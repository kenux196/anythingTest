package org.kenux.anything.domain.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CarDto {
    private String make;
    private int seatCount;
    private String type;
}