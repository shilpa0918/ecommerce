package com.docker.spring.app.domains;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AddCategoryRequest {
    private String identifier;
    private String name;
    private String description;
    private Integer markForDelete;
    private Integer published;
}
