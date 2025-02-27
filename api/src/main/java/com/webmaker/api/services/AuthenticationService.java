package com.webmaker.api.services;

import com.webmaker.api.dtos.CredentialsDto;
import com.webmaker.api.dtos.UserRequestDto;
import com.webmaker.api.dtos.UserResponseDto;

public interface AuthenticationService {
    UserResponseDto login(CredentialsDto credentialsDto);

    UserResponseDto registerUser(UserRequestDto userRequestDto);
}
