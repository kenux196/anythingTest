package org.kenux.anything.domain.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class RedisUserDto implements Serializable {
    private String id;
    private String name;
    private Integer age;
}