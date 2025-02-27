package com.webmaker.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class VideoResponseDto {
    private Long id;
    private String videoUrl;
    private Instant uploadedAt;
}