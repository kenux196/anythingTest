package org.kenux.anything.mapper;

import org.kenux.anything.domain.dto.AddressDto;
import org.kenux.anything.domain.dto.MemberDto;
import org.kenux.anything.domain.entity.Address;
import org.kenux.anything.domain.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

//    @Mapping(source = "address", target = "address")
    MemberDto toMemberDto(Member member);

    AddressDto toAddressDto(Address address);

    Member toMemberEntity(MemberDto memberDto);
}
