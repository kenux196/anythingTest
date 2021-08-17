package org.kenux.anything.service.impl;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.kenux.anything.domain.dto.MemberDto;
import org.kenux.anything.domain.entity.Member;
import org.kenux.anything.domain.entity.enums.MemberType;
import org.kenux.anything.repository.MemberRepository;
import org.kenux.anything.service.MemberService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    public void deleteMembers(List<Long> ids) {
        memberRepository.deleteAllById(ids);
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
}
