package org.kenux.anything.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.kenux.anything.domain.entity.Member;
import org.kenux.anything.domain.entity.enums.Authority;

@Data
@AllArgsConstructor
public class MemberResponseDto {

    private String email;
    private Authority authority;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getEmail(), member.getAuthority());
    }
}