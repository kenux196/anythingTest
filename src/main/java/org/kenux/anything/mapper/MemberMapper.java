package org.kenux.anything.mapper;

import org.kenux.anything.domain.dto.AddressDto;
import org.kenux.anything.domain.dto.MemberDto;
import org.kenux.anything.domain.entity.Address;
import org.kenux.anything.domain.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper extends GenericMapper<MemberDto, Member> {

    //    @Mapping(source = "address", target = "address")
    MemberDto toMemberDto(Member member);

    AddressDto toAddressDto(Address address);

    Member toMemberEntity(MemberDto memberDto);
}
