package org.kenux.anything.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "room_info")
public class Room {

    @Id
    private String number;
    private String name;

    @Column(name = "description")
    private String desc;

    @Transient
    private final long timestamp =  System.currentTimeMillis();

    protected Room() {

    }

    public Room(String number, String name, String desc) {
        this.number = number;
        this.name = name;
        this.desc = desc;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
