package com.webmaker.api.dtos;

import lombok.Data;

@Data
public class ProfileDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String bio;
}