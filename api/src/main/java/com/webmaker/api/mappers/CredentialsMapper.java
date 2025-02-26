package com.webmaker.api.mappers;

import com.webmaker.api.dtos.CredentialsDto;
import com.webmaker.api.entities.Credentials;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {

    CredentialsDto entityToDto(Credentials credentials);

    Credentials dtoToEntity(CredentialsDto credentialsDto);
}
