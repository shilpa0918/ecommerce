package com.docker.spring.app.domains;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private String partNumber;
    private String name;
    private String description;
    private Integer inventory;
    private BigDecimal offerPrice;
    private BigDecimal listPrice;
    private String brand;

}
