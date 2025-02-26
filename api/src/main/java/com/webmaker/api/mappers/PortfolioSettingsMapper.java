package com.webmaker.api.mappers;

import com.webmaker.api.dtos.PortfolioSettingsDto;
import com.webmaker.api.entities.PortfolioSettings;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PortfolioSettingsMapper {

    PortfolioSettingsDto entityToDto(PortfolioSettings settings);

    PortfolioSettings dtoToEntity(PortfolioSettingsDto settingsDto);
}
