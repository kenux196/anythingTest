package org.kenux.anything.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.kenux.anything.domain.entity.Member;

import java.util.List;

import static org.kenux.anything.domain.entity.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Member> findAllMemberList() {
        return jpaQueryFactory.selectFrom(member).fetch();
    }
}