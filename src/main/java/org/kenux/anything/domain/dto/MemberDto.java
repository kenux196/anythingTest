package org.kenux.anything.domain.dto;

import lombok.*;
import org.kenux.anything.domain.entity.Team;

@Data
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
    private AddressDto Address;
    private String password;
    private Team team;
//    private String teamName;
}