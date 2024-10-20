package com.docker.spring.app.domains;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AddProductRequest {
    private String partNumber;
}
