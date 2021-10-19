package org.kenux.anything.domain.entity;

import lombok.*;
import org.kenux.anything.domain.entity.enums.MemberType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

//    @Enumerated(EnumType.STRING)
//    private Authority authority;

    @Column(name = "activated")
    private Boolean activated;

    @ManyToMany
    @JoinTable(
            name = "Member_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @Column(name = "age")
    private int age;

    @Embedded
    private Address address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_type")
    private MemberType memberType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String name) {
        this.name = name;
    }

    public Member(String email, String password, Authority authority) {
        this.email = email;
        this.password = password;
//        this.authority = authority;
    }

    // 연관관계 편의 메소드
    public void changeTeam(Team team) {
        // 팀에 소속되어 있으면 이전 팀을 지워줘야 한다.
        if (this.team != null) {
            this.team.getMembers().remove(this);
        }
        this.team = team;
        team.getMembers().add(this);
    }

    public String getMemberAddress() {
        return "기본주소 : " + address.getAddress1() + " " +
                "상세주소 : " + address.getAddress2() + " " +
                "우편번호 : " + address.getZipCode();
    }

    public void changeAddress(Address address) {
        this.address = address;
    }

    public boolean isActivated() {
        return this.activated;
    }
}