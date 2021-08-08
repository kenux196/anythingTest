package org.kenux.anything.domain.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Address {

    private String zipCode;
    private String address1;
    private String address2;
}