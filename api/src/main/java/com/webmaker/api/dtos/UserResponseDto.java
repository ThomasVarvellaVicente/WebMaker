package com.webmaker.api.dtos;

import lombok.Data;
import java.time.Instant;
import java.util.Set;

@Data
public class UserResponseDto {
    private Long id;
    private ProfileDto profile;
    private boolean active;
    private Instant createdAt;
    private Set<PortfolioResponseDto> portfolios;
}

