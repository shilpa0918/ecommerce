package com.docker.spring.app.domains;

import lombok.*;

import java.sql.Blob;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ImageDTO {
    private String fileName;
    private String fileType;
    private Blob image;
    private String downloadUrl;
}
