package org.kenux.anything.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.kenux.anything.domain.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.kenux.anything.domain.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public List<Member> findByMemberName(String name) {
        return jpaQueryFactory.selectFrom(member)
                .where(member.name.eq(name))
                .fetch();
    }
}