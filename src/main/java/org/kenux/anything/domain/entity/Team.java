package org.kenux.anything.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 서비스 명   : anythingTest
 * 패키지 명   : org.kenux.anything.domain.entity
 * 클래스 명   : Team
 * 설명       :
 *
 * ====================================================================================
 *
 * </pre>
 **/

@Entity
@Table(name = "team")
@Getter
@ToString(exclude = "members")
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<Member> members = new ArrayList<>();

    public void addMember(Member member) {
        members.add(member);
    }

    public Team(String name) {
        this.name = name;
    }
}