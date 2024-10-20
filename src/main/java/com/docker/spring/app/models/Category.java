package com.docker.spring.app.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="catgroup")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    private String identifier;
    private String name;
    private String description;
    private Integer markForDelete;
    private Integer published;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
