package org.kenux.anything.web.dto;

import lombok.*;
import org.kenux.anything.domain.entity.Address;
import org.kenux.anything.domain.entity.Member;
import org.kenux.anything.domain.entity.Team;
import org.kenux.anything.domain.entity.enums.MemberType;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String name;
    private String email;
    private int age;
    private String phoneNumber;
    private AddressDto address;
    private String password;
    private MemberType memberType;
    private TeamDto team;

    public static MemberDto of(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .age(member.getAge())
                .email(member.getEmail())
                .address(member.getAddress().toDto())
                .password(member.getPassword())
                .phoneNumber(member.getPhoneNumber())
                .memberType(member.getMemberType())
                .team(TeamDto.of(member.getTeam()))
                .build();
    }

    public Member toEntity() {
        final Member member = Member.builder()
                .name(name)
                .age(age)
                .email(email)
                .phoneNumber(phoneNumber)
                .address(new Address(address.getAddress1(), address.getAddress2(), address.getZipCode()))
                .memberType(memberType)
                .password(password)
                .build();
        member.changeTeam(new Team(team.getName()));
        return member;
    }
}