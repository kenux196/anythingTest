package org.kenux.anything.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AddressDto {
    String address1;
    String address2;
    String zipCode;
}
