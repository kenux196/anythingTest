package org.kenux.anything.mapper;

import org.junit.jupiter.api.Test;
import org.kenux.anything.domain.dto.CarDto;
import org.kenux.anything.domain.entity.Car;
import org.kenux.anything.domain.entity.enums.CarType;

import static org.assertj.core.api.Assertions.assertThat;

class CarMapperTest {

    @Test
    public void shouldMapCarToDto() {
        // given
        Car car = new Car("Morris", 5, CarType.Sedan);

        // when
        CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);

        // then
        assertThat(carDto).isNotNull();
        assertThat(carDto.getMake()).isEqualTo("Morris");
        assertThat(carDto.getSeatCount()).isEqualTo(5);
        assertThat(carDto.getType()).isEqualTo("Sedan");
    }

}