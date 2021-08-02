package org.kenux.anything.domain.entity;

import javax.persistence.*;

/**
 * <pre>
 * 서비스 명   : anythingTest
 * 패키지 명   : org.kenux.anything.domain.entity
 * 클래스 명   : Member
 * 설명       :
 *
 * ====================================================================================
 *
 * </pre>
 **/
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private int age;
//    private Address address;

    @Column(name = "phone_number")
    private String phoneNumber;
}