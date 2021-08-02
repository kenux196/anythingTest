package org.kenux.anything.domain.dto;

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
