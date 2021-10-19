package org.kenux.anything.service.impl;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kenux.anything.domain.entity.Member;
import org.kenux.anything.domain.entity.enums.MemberType;
import org.kenux.anything.repository.MemberRepository;
import org.kenux.anything.service.MemberService;
import org.kenux.anything.web.dto.MemberDto;
import org.kenux.anything.web.dto.MemberResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void createMember(MemberDto memberDto) {
        // TODO : Convert memberDto to Member entity   - skyun 2021/08/06
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public void deleteMembers(List<Member> members) {
        memberRepository.deleteAll(members);
        // TODO : 아래 메소드도 활용해보자   - skyun 2021/08/06
//        memberRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void updateMemberInfo(MemberDto memberDto) {

    }

    @Override
    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMember(Long id) throws Exception {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Member not founded."));

    }

    @Override
    public List<Member> getMembers(List<Long> id) {
        return null;
    }

    @Override
    public List<Member> getMemberByName(String name) {
        return null;
    }

    @Override
    public List<Member> getMemberByType(MemberType memberType) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDto getMemberInfo(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDto getMyInfo() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("Security Context 에 인증 정보가 없습니다.");
        }
        log.info("authentication.getName() : " + authentication.getName());

        return memberRepository.findByEmail(authentication.getName())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }
}
