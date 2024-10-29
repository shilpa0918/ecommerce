package com.docker.spring.app.domains;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PriceDto {
    private BigDecimal price;
    private String currency;
    private String priceType;
    private Date createdAt;
    private Date updatedAt;
}
