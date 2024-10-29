package com.docker.spring.app.domains;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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

    private CategoryDTO category;
    private List<ImageDTO> images;
    private List<PriceDto> price;
    private List<InventoryDto> inventories;

}
