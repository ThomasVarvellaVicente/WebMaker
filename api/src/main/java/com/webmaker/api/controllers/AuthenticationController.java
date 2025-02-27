package com.webmaker.api.controllers;

import com.webmaker.api.dtos.CredentialsDto;
import com.webmaker.api.dtos.UserRequestDto;
import com.webmaker.api.dtos.UserResponseDto;
import com.webmaker.api.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public UserResponseDto registerUser(@RequestBody UserRequestDto userRequestDto) {
        return authenticationService.registerUser(userRequestDto);
    }

    @PostMapping("/login")
    public UserResponseDto login(@RequestBody CredentialsDto credentialsDto){
        return authenticationService.login(credentialsDto);
    }
}
