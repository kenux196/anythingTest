package org.kenux.anything.repository;


import org.kenux.anything.domain.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findAllMemberList();
}
