package org.kenux.anything.web.dto;

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
