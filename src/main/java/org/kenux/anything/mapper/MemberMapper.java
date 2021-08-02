package org.kenux.anything.mapper;

import org.kenux.anything.domain.dto.MemberDto;
import org.kenux.anything.domain.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <pre>
 * 서비스 명   : anythingTest
 * 패키지 명   : org.kenux.anything.mapper
 * 클래스 명   : MemberMapper
 * 설명       :
 *
 * ====================================================================================
 *
 * </pre>
 **/

@Mapper(componentModel = "spring")
public interface MemberMapper extends GenericMapper<MemberDto, Member> {

    Member toMemberEntity(MemberDto memberDto);
    MemberDto toMemberDto(Member member);
}
