package org.kenux.anything.repository;

import org.junit.jupiter.api.Test;
import org.kenux.anything.domain.dto.MemberDto;
import org.kenux.anything.domain.dto.TeamDto;
import org.kenux.anything.domain.entity.Address;
import org.kenux.anything.domain.entity.Member;
import org.kenux.anything.domain.entity.Team;
import org.kenux.anything.mapper.MemberMapper;
import org.kenux.anything.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void saveMemberTest() {
        Team team = new Team("teamA");
        teamRepository.save(team);
        System.out.println("팀 저장");

        Member member = Member.builder()
                .name("kenux")
                .email("kenux.yun@gmail.com")
                .age(44)
                .password("1111")
                .phoneNumber("010-1234-1234")
                .build();
        member.changeTeam(team);
        final Member savedMember = memberRepository.save(member);
        System.out.println("savedMember = " + savedMember);

        final Member findMember = memberRepository.findByName(member.getName());
        System.out.println("findMember = " + findMember);
        System.out.println("findMember.getTeam().getClass() = " + findMember.getTeam().getClass());
        System.out.println("findMember.getTeam().getName() = " + findMember.getTeam());
        
        assertThat(findMember.getName()).isEqualTo(savedMember.getName());
        assertThat(findMember.getId()).isEqualTo(savedMember.getId());
    }

    @Test
    @Transactional
    void saveMemberTest2() {
        Team team = new Team("teamA");
        teamRepository.save(team);

        Member member1 = new Member("member1");
        memberRepository.save(member1);
        Member member2 = new Member("member2");
        memberRepository.save(member2);

//        team.getMembers().add(member1);
//        team.getMembers().add(member2);

        member1.changeTeam(team);
        member2.changeTeam(team);

        System.out.println("team.getMembers() = " + team.getMembers());
        System.out.println("member2.getTeam().getName() = " + member2.getTeam().getName());
    }

    @Test
    @Transactional
    void embeddedEntityTest() {
        Member member = Member.builder()
                .name("member1")
                .address(new Address("대구", "달성군"))
                .build();
        memberRepository.save(member);
        System.out.println("member = " + member);
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
        teamRepository.save(team);
        member.changeTeam(team);
        memberRepository.save(member);

        MemberDto memberDto = memberMapper.toMemberDto(member);
        System.out.println("memberDto = " + memberDto);

        TeamDto teamDto = teamMapper.toDto(team);
        System.out.println("teamDto = " + teamDto);

    }
}