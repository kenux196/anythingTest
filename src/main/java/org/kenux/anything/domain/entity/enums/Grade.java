package org.kenux.anything.domain.entity.enums;

public enum Grade {
    STAR_1("1성"),
    STAR_2("2성"),
    STAR_3("3성"),
    STAR_4("4성"),
    STAR_5("5성");

    private String name;

    Grade(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
