package org.kenux.anything.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kenux.anything.domain.entity.Authority;
import org.kenux.anything.domain.entity.Member;
import org.kenux.anything.domain.entity.enums.RoleType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String name;
    private String email;
    private String password;

    public Member toMember(PasswordEncoder passwordEncoder) {
        final Authority authority = Authority.builder().authorityName(RoleType.ROLE_USER.toString()).build();
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        return Member.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .authorities(authorities)
                .activated(true)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}