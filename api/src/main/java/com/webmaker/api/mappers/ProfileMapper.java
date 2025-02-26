package com.webmaker.api.mappers;

import com.webmaker.api.dtos.ProfileDto;
import com.webmaker.api.entities.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileDto entityToDto(Profile profile);

    Profile dtoToEntity(ProfileDto profileDto);
}