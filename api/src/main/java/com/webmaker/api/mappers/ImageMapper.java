package com.webmaker.api.mappers;

import com.webmaker.api.dtos.ImageRequestDto;
import com.webmaker.api.dtos.ImageResponseDto;
import com.webmaker.api.entities.Image;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    ImageResponseDto entityToResponseDto(Image image);

    Set<ImageResponseDto> entitiesToResponseDtos(Set<Image> images);

    Image requestDtoToEntity(ImageRequestDto imageRequestDto);
}