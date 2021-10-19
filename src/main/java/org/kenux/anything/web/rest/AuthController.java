package org.kenux.anything.web.rest;

import lombok.RequiredArgsConstructor;
import org.kenux.anything.domain.entity.Member;
import org.kenux.anything.service.AuthService;
import org.kenux.anything.web.dto.MemberRequestDto;
import org.kenux.anything.web.dto.MemberResponseDto;
import org.kenux.anything.web.dto.TokenDto;
import org.kenux.anything.web.dto.TokenRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")  // user, admin 모두 호출 가능
    public ResponseEntity<Member> getMyUserInfo() {
        final Optional<Member> myUserWithAuthorities = authService.getMyUserWithAuthorities();
        return myUserWithAuthorities.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")  // admin만 호출 가능
    public ResponseEntity<Member> getMemberInfo(@PathVariable String username) {
        final Optional<Member> userWithAuthorities = authService.getUserWithAuthorities(username);
        return userWithAuthorities.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}