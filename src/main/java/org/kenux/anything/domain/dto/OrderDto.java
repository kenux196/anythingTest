package org.kenux.anything.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class OrderDto extends BaseTimeDto {

    private Long id;
    private String orderName;
    private LocalDateTime createdDate;
}
