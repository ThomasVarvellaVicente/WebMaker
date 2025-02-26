package com.webmaker.api.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
public class PortfolioSettings {

    private String themeColor = "#FFFFFF"; // Default: White
    private String layoutStyle = "grid"; // Options: grid, list, masonry
    private boolean showContactInfo = true;
}

