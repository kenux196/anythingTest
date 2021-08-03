package org.kenux.anything.domain.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String address1;
    private String address2;

    protected Address() {
    }

    public Address(String address1, String address2) {
        this.address1 = address1;
        this.address2 = address2;
    }

    @Override
    public String toString() {
        return address1 + " " + address2;
    }
}