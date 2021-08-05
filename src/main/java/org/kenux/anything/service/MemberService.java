package org.kenux.anything.service;

import javassist.NotFoundException;
import org.kenux.anything.domain.dto.MemberDto;
import org.kenux.anything.domain.entity.Member;
import org.kenux.anything.domain.entity.MemberType;

import java.util.List;

public interface MemberService {

    void createMember(MemberDto memberDto);

    void deleteMember(Long id);

    void deleteMembers(List<Long> ids);

    void updateMemberInfo(MemberDto memberDto);

    List<Member> getAllMember();

    Member getMember(Long id) throws Exception;

    List<Member> getMembers(List<Long> id);

    List<Member> getMemberByName(String name);

    List<Member> getMemberByType(MemberType memberType);

    // TODO : 멤버 조회 시, 결과는 현재는 엔티티로 하고 있지만, DTO로 변환하여 제공하는 코드도 추가하자   - skyun 2021/08/06
    // TODO : 리파지토리에서 멤버 조회 시, DTO로 조회하거나, projection을 사용하는 부분도 직접해 보자   - skyun 2021/08/06
    // TODO : 다양하게 JPA를 활용해 보자.   - skyun 2021/08/06
}
