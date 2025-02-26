package com.webmaker.api.mappers;

import com.webmaker.api.dtos.UserRequestDto;
import com.webmaker.api.dtos.UserResponseDto;
import com.webmaker.api.entities.User;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring", uses = { ProfileMapper.class, CredentialsMapper.class, PortfolioMapper.class })
public interface UserMapper {

    UserResponseDto entityToResponseDto(User user);

    Set<UserResponseDto> entitiesToResponseDtos(Set<User> users);

    User requestDtoToEntity(UserRequestDto userRequestDto);
}
