package org.kenux.anything.domain.repository;

import org.kenux.anything.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <pre>
 * 서비스 명   : anythingTest
 * 패키지 명   : org.kenux.anything.domain.repository
 * 클래스 명   : MemberRepository
 * 설명       :
 *
 * ====================================================================================
 *
 * </pre>
 **/
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByName(String name);
}