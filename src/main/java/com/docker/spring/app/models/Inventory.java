package com.docker.spring.app.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int inventoryId;
    private int FMCenter;
    private int quantity;
    private int expectedQuantity;
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;
}
