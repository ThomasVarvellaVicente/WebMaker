package com.webmaker.api.mappers;

import com.webmaker.api.dtos.PortfolioRequestDto;
import com.webmaker.api.dtos.PortfolioResponseDto;
import com.webmaker.api.entities.Portfolio;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring", uses = { PortfolioSettingsMapper.class, ImageMapper.class, VideoMapper.class })
public interface PortfolioMapper {

    PortfolioResponseDto entityToResponseDto(Portfolio portfolio);

    Set<PortfolioResponseDto> entitiesToResponseDtos(Set<Portfolio> portfolios);

    Portfolio requestDtoToEntity(PortfolioRequestDto portfolioRequestDto);
}
