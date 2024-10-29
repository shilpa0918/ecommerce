package com.docker.spring.app.domains;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ImageDTO {
    private String fileName;
    private String fileType;
    private String image;
    private String downloadUrl;
    private Long productId;
}
