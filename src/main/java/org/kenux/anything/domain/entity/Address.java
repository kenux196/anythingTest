package org.kenux.anything.domain.entity;

import lombok.*;

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
}