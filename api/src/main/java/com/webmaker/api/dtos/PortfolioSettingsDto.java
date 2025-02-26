package com.webmaker.api.dtos;

import lombok.Data;

@Data
public class PortfolioSettingsDto {
    private String themeColor;
    private String layoutStyle;
    private boolean showContactInfo;
}