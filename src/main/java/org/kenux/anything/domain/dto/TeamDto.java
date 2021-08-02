package org.kenux.anything.domain.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 서비스 명   : anythingTest
 * 패키지 명   : org.kenux.anything.domain.dto
 * 클래스 명   : TeamDto
 * 설명       :
 *
 * ====================================================================================
 *
 * </pre>
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TeamDto {

    private Long id;
    private String name;
    private List<MemberDto> members;
}