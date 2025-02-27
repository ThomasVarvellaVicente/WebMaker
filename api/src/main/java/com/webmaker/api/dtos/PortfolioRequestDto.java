package com.webmaker.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PortfolioRequestDto {
    private String title;
    private String description;
    private boolean isPublic;
    private PortfolioSettingsDto settings;
}
