package com.docker.spring.app.domains;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class InventoryDto {
    private int FMCenter;
    private int quantity;
    private int expectedQuantity;
    private Date createdAt;
    private Date updatedAt;
}
