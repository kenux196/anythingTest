package org.kenux.anything.web.dto;

import lombok.*;
import org.kenux.anything.domain.entity.Team;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TeamDto {

    private Long id;
    private String name;
    private List<MemberDto> members;

    public TeamDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static TeamDto of(Team team) {
        return new TeamDto(team.getId(), team.getName());
    }

    public Team toEntity() {
        return new Team(name);
    }
}