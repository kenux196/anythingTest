package org.kenux.anything.web.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class BaseTimeDto {

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
