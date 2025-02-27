package com.webmaker.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class ImageResponseDto {
    private Long id;
    private String imageUrl;
    private Instant uploadedAt;
}
