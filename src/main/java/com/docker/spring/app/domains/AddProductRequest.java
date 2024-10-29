package com.docker.spring.app.domains;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AddProductRequest {
    private String partNumber;
    private String name;
    private String description;
    private Integer inventory;
    private BigDecimal offerPrice;
    private BigDecimal listPrice;
    private String brand;
    private Long categoryId;
    private List<ImageDTO> images;
    private List<PriceDto> priceDtos;
    private List<InventoryDto> inventoryDtos;
}
