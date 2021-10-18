package org.kenux.anything.repository;

import org.kenux.anything.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    List<Member> findByName(String member1);

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
}