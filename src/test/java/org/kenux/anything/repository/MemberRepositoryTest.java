package org.kenux.anything.repository;

import org.junit.jupiter.api.Test;
import org.kenux.anything.domain.dto.MemberDto;
import org.kenux.anything.domain.dto.TeamDto;
import org.kenux.anything.domain.entity.Member;
import org.kenux.anything.domain.entity.Team;
import org.kenux.anything.mapper.MemberMapper;
import org.kenux.anything.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

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

    @Test
    void saveMemberTest() {
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
        teamRepository.save(team);
        member.changeTeam(team);
        memberRepository.save(member);

        MemberDto memberDto = memberMapper.toMemberDto(member);
        System.out.println("memberDto = " + memberDto);

        TeamDto teamDto = teamMapper.toDto(team);
        System.out.println("teamDto = " + teamDto);

    }

}