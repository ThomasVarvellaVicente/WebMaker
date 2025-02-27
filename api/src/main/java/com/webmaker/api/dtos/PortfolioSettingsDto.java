package com.webmaker.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PortfolioSettingsDto {
    private String themeColor;
    private String layoutStyle;
    private boolean showContactInfo;
}