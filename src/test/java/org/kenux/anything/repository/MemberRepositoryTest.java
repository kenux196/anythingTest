package org.kenux.anything.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kenux.anything.web.dto.MemberDto;
import org.kenux.anything.domain.entity.Address;
import org.kenux.anything.domain.entity.Member;
import org.kenux.anything.domain.entity.Team;
import org.kenux.anything.domain.entity.enums.MemberType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED) // 실제 DB 사용하고 싶을때 NONE 사용
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberRepositorySupport memberRepositorySupport;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    @DisplayName(value = "멤버 저장 테스트")
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

        final Optional<Member> result = memberRepository.findById(member.getId());

        assertThat(result).isNotEmpty();

        result.ifPresent(findMember -> {
            System.out.println("findMember = " + findMember);
            System.out.println("findMember.getTeam().getClass() = " + findMember.getTeam().getClass());
            System.out.println("findMember.getTeam().getName() = " + findMember.getTeam());
            assertThat(findMember.getName()).isEqualTo(savedMember.getName());
            assertThat(findMember.getId()).isEqualTo(savedMember.getId());
        });
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
                .address(new Address("12344", "대구", "달성군"))
                .build();
        memberRepository.save(member);
        System.out.println("member = " + member);
    }

    @Test
    @Transactional
    void findUsingQueryDSLTest() {
        Member member1 = new Member("member1");
        memberRepository.save(member1);
        Member member2 = new Member("member2");
        memberRepository.save(member2);

        final List<Member> result = memberRepositorySupport.findByMemberName(member1.getName());
        for (Member member : result) {
            System.out.println("member = " + member);
        }

        final List<Member> allMemberList = memberRepository.findAllMemberList();
        for (Member member : allMemberList) {
            System.out.println("member = " + member);
        }
    }

    @Test
    @Transactional
    void memberTypeTest() {
        Member member1 = Member.builder()
                .name("memberTypeTest1")
                .memberType(MemberType.MEMBER)
                .build();
        memberRepository.save(member1);

        Member member2 = Member.builder()
                .name("memberTypeTest2")
                .memberType(MemberType.ADMIN)
                .build();
        memberRepository.save(member2);

        List<Member> foundMembers = memberRepository.findByName("memberTypeTest1");
        assertThat(foundMembers.size()).isEqualTo(1);
        assertThat(foundMembers.get(0).getName()).isEqualTo("memberTypeTest1");
        assertThat(foundMembers.get(0).getMemberType()).isEqualTo(MemberType.MEMBER);

        List<Member> foundMembers2 = memberRepository.findByName("memberTypeTest2");
        assertThat(foundMembers2.size()).isEqualTo(1);
        assertThat(foundMembers2.get(0).getName()).isEqualTo("memberTypeTest2");
        assertThat(foundMembers2.get(0).getMemberType()).isEqualTo(MemberType.ADMIN);
    }

    @Test
    @Transactional
    void memberCreatedDateTest() {
        LocalDateTime current = LocalDateTime.now();

        Member member = Member.builder()
                .name("kenux")
                .memberType(MemberType.TEAM_MANAGER)
                .createdDate(current)
                .build();

        Member savedMember = memberRepository.save(member);

        Optional<Member> found = memberRepository.findById(savedMember.getId());
        assertThat(found).isNotNull();
        found.ifPresent(foundMember -> {
            assertThat(foundMember.getName()).isEqualTo("kenux");
            assertThat(foundMember.getCreatedDate()).isEqualTo(current);
        });
    }

//    @Autowired
//    MemberMapper memberMapper;

    @Test
    @Transactional
    void convertDtoTest() {
        Team team = new Team("teamA");
        LocalDateTime current = LocalDateTime.now();
        Member member = Member.builder()
                .name("kenux")
                .email("kenux.yun@gmail.com")
                .memberType(MemberType.TEAM_MANAGER)
                .address(new Address("12311", "대구", "달성군"))
                .phoneNumber("010-1234-0987")
                .age(44)
                .password("1234")
                .createdDate(current)
                .updatedDate(current)
                .build();
        member.changeTeam(team);

        final MemberDto memberDto = MemberDto.of(member);
        System.out.println("memberDto = " + memberDto);

//        final MemberDto memberDto1 = memberMapper.toDto(member);
//        System.out.println("memberDto1 = " + memberDto1);

    }
}