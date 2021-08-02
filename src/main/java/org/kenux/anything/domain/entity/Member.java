package org.kenux.anything.domain.entity;

import lombok.*;

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
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Getter
@ToString(exclude = "team")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Team team;

    public void setTeam(Team team) {
        this.team = team;
        team.addMember(this);
    }
}