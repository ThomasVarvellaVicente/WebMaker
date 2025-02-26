package com.webmaker.api.mappers;

import com.webmaker.api.dtos.VideoRequestDto;
import com.webmaker.api.dtos.VideoResponseDto;
import com.webmaker.api.entities.Video;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    VideoResponseDto entityToResponseDto(Video video);

    Set<VideoResponseDto> entitiesToResponseDtos(Set<Video> videos);

    Video requestDtoToEntity(VideoRequestDto videoRequestDto);
}
