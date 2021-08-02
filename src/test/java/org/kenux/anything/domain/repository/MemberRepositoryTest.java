package org.kenux.anything.domain.repository;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kenux.anything.domain.dto.MemberDto;
import org.kenux.anything.domain.dto.TeamDto;
import org.kenux.anything.domain.entity.Member;
import org.kenux.anything.domain.entity.Team;
import org.kenux.anything.mapper.MemberMapper;
import org.kenux.anything.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <pre>
 * 서비스 명   : anythingTest
 * 패키지 명   : org.kenux.anything.domain.repository
 * 클래스 명   : MemberRepositoryTest
 * 설명       :
 *
 * ====================================================================================
 *
 * </pre>
 **/

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamMapper teamMapper;

    @Test
    void saveMemberTest() {
        Member member = Member.builder()
                .name("kenux")
                .email("kenux.yun@gmail.com")
                .age(44)
                .password("1111")
                .phoneNumber("010-1234-1234")
                .build();

        final Member savedMember = memberRepository.save(member);
        final Member result = memberRepository.findByName(member.getName());

        assertThat(result.getName()).isEqualTo(savedMember.getName());
        assertThat(result.getId()).isEqualTo(savedMember.getId());
    }
    
    @Test
    void convertDtoTest() {
        Member member = Member.builder()
                .name("kenux")
                .email("kenux.yun@gmail.com")
                .age(44)
                .password("1111")
                .phoneNumber("010-1234-1234")
                .build();

        Team team = new Team("teamA");
        member.setTeam(team);

        final MemberDto memberDto = MemberMapper.instance.toMemberDto(member);
        System.out.println("memberDto = " + memberDto);

        TeamDto teamDto = teamMapper.toDto(team);
        System.out.println("teamDto = " + teamDto);

    }

}