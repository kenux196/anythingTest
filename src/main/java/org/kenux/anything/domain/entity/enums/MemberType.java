package org.kenux.anything.domain.entity.enums;

public enum MemberType {
    ADMIN("관리자"),
    TEAM_MANAGER("팀 매니저"),
    MEMBER("멤버"),
    GUEST("게스트");

    private String name;

    MemberType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
