package com.docker.spring.app.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "catentry")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long productId;
    private String partNumber;
    private String name;
    private String description;
    private BigDecimal offerPrice;
    private BigDecimal listPrice;
    private String brand;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;


}
