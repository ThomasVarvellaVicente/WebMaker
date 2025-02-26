package com.webmaker.api.dtos;

import lombok.Data;
import java.time.Instant;

@Data
public class VideoResponseDto {
    private Long id;
    private String videoUrl;
    private Instant uploadedAt;
}