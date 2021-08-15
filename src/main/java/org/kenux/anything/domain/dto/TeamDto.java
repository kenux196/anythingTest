package org.kenux.anything.domain.dto;

import lombok.*;

import java.util.List;

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