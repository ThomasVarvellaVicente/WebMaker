package com.webmaker.api.services;

import com.webmaker.api.dtos.CredentialsDto;
import com.webmaker.api.dtos.ProfileDto;
import com.webmaker.api.dtos.UserRequestDto;
import com.webmaker.api.dtos.UserResponseDto;

public interface UserService {
    UserResponseDto getUser(String username);
    UserResponseDto updateUserProfile(String username, ProfileDto profileDto);
    UserResponseDto updateUserCredentials(String username, CredentialsDto credentialsDto);
    UserResponseDto updateUserAdminStatus(String username, boolean adminStatus);
    UserResponseDto updateUserActiveStatus(String username, boolean activeStatus);
}
