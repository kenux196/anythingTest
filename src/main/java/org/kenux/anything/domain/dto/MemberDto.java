package org.kenux.anything.domain.dto;

import jdk.jfr.DataAmount;
import lombok.*;
import org.kenux.anything.domain.entity.Team;

/**
 * <pre>
 * 서비스 명   : anythingTest
 * 패키지 명   : org.kenux.anything.domain.dto
 * 클래스 명   : MemberDto
 * 설명       :
 *
 * ====================================================================================
 *
 * </pre>
 **/

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