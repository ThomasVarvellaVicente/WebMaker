package com.webmaker.api.dtos;

import lombok.Data;
import java.time.Instant;

@Data
public class ImageResponseDto {
    private Long id;
    private String imageUrl;
    private Instant uploadedAt;
}
