package com.webmaker.api.controllers;

import com.webmaker.api.dtos.CredentialsDto;
import com.webmaker.api.dtos.ProfileDto;
import com.webmaker.api.dtos.UserRequestDto;
import com.webmaker.api.dtos.UserResponseDto;
import com.webmaker.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public UserResponseDto getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

    @PatchMapping("/{username}/profile")
    public UserResponseDto editUserProfile(@PathVariable String username, @RequestBody ProfileDto profileDto) {
        return userService.updateUserProfile(username, profileDto);
    }

    @PatchMapping("/{username}/credentials")
    public UserResponseDto editUserCredentials(@PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
        return userService.updateUserCredentials(username, credentialsDto);
    }

    @PatchMapping("/{username}/admin/{adminStatus}")
    public UserResponseDto editUserAdmin(@PathVariable String username, @PathVariable boolean adminStatus) {
        return userService.updateUserAdminStatus(username, adminStatus);
    }

    @PatchMapping("/{username}/active/{activeStatus}")
    public UserResponseDto editUserActive(@PathVariable String username, @PathVariable boolean activeStatus) {
        return userService.updateUserActiveStatus(username, activeStatus);
    }
}
