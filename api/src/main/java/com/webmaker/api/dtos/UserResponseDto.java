package com.webmaker.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private ProfileDto profile;
    private Set<String> roles;
    private boolean active;
    private boolean admin;
}

