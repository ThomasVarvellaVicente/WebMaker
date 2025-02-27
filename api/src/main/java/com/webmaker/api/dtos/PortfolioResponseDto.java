package com.webmaker.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@NoArgsConstructor
@Data
public class PortfolioResponseDto {
    private Long id;
    private String title;
    private String description;
    private boolean isPublic;
    private Instant createdAt;
    private PortfolioSettingsDto settings;
    private Set<ImageResponseDto> images;
    private Set<VideoResponseDto> videos;
}
