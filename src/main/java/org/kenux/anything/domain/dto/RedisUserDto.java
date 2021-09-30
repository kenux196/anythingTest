package org.kenux.anything.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@ToString
public class RedisUserDto implements Serializable {
    private String id;
    private String pw;
}