package org.kenux.anything.domain.entity;

import lombok.*;
import org.kenux.anything.web.dto.AddressDto;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Address {

    private String zipCode;
    private String address1;
    private String address2;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Address address = (Address) obj;
        return Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(address1, address.address1) &&
                Objects.equals(address2, address.address2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, address1, address2);
    }

    public AddressDto toDto() {
        return AddressDto.builder()
                .address1(this.address1)
                .address2(this.address2)
                .zipCode(this.zipCode)
                .build();
    }

    public static AddressDto toAddressDto(Address address) {
        return new AddressDto(address.getAddress1(), address.getAddress2(), address.getZipCode());
    }
}