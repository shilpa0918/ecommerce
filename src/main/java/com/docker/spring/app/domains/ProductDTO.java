package com.docker.spring.app.domains;

import lombok.*;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String productId;
    private String partNumber;
}
