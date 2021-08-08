package org.kenux.anything.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "hotel")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Grade grade;

    private Address address;
//    private String zipCode;
//    private String address1;
//    private String address2;


    public Hotel(String name, Grade grade, Address address) {
        this.name = name;
        this.grade = grade;
        this.address = address;
    }
}
