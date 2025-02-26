package com.webmaker.api.dtos;

import lombok.Data;

@Data
public class UserRequestDto {
    private CredentialsDto credentials;
    private ProfileDto profile;
}
