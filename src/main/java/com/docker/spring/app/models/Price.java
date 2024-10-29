package com.docker.spring.app.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int priceId;
    private BigDecimal price;
    private String currency;
    private String priceType;
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;
}
