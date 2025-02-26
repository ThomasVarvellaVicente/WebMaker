package com.webmaker.api.dtos;

import lombok.Data;

@Data
public class PortfolioRequestDto {
    private String title;
    private String description;
    private boolean isPublic;
    private PortfolioSettingsDto settings;
}
