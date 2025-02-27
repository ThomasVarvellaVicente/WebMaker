package com.webmaker.api.services.serviceImpl;

import com.webmaker.api.dtos.*;
import com.webmaker.api.entities.Credentials;
import com.webmaker.api.entities.Role;
import com.webmaker.api.entities.User;
import com.webmaker.api.exceptions.BadRequestException;
import com.webmaker.api.exceptions.NotFoundException;
import com.webmaker.api.mappers.ProfileMapper;
import com.webmaker.api.repositories.UserRepository;
import com.webmaker.api.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new user (no JWT token returned).
     */
    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        // 1. Validate request
        if (userRequestDto == null
                || userRequestDto.getCredentials() == null
                || userRequestDto.getCredentials().getUsername() == null
                || userRequestDto.getCredentials().getPassword() == null
        ) {
            throw new BadRequestException("A username and password are required.");
        }

        // 2. Check if username already exists
        String newUsername = userRequestDto.getCredentials().getUsername();
        if (userRepository.findByCredentialsUsername(newUsername).isPresent()) {
            throw new BadRequestException("A user with this username already exists.");
        }

        // 3. Create a new User entity
        User newUser = new User();
        newUser.setActive(true);

        // 3a. Set Credentials (encrypt password)
        Credentials creds = new Credentials();
        creds.setUsername(newUsername);
        creds.setPassword(passwordEncoder.encode(userRequestDto.getCredentials().getPassword()));
        newUser.setCredentials(creds);

        // 3b. Set Profile (if provided)
        if (userRequestDto.getProfile() != null) {
            newUser.setProfile(profileMapper.dtoToEntity(userRequestDto.getProfile()));
        }

        // 4. Save to DB
        User savedUser = userRepository.saveAndFlush(newUser);

        // 5. Build a UserResponseDto (no token)
        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getCredentials().getUsername(),
                profileMapper.entityToDto(savedUser.getProfile()),
                savedUser.getRoles().stream()
                        .map(Role::getAuthority)
                        .collect(Collectors.toSet()),
                savedUser.isActive(),
                savedUser.isAdmin()
        );
    }

    /**
     * Logs in an existing user (no JWT token returned).
     */
    @Override
    public UserResponseDto login(CredentialsDto credentialsDto) {
        // 1. Validate input
        if (credentialsDto == null
                || credentialsDto.getUsername() == null
                || credentialsDto.getPassword() == null
        ) {
            throw new BadRequestException("A username and password are required.");
        }

        // 2. Fetch user from DB
        User user = userRepository.findByCredentialsUsernameAndActiveTrue(credentialsDto.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found or inactive."));

        // 3. Compare passwords manually
        //    (Assumes you have a 'passwordEncoder' field that can match raw vs encoded)
        if (!passwordEncoder.matches(credentialsDto.getPassword(), user.getCredentials().getPassword())) {
            throw new BadRequestException("Invalid password.");
        }

        // 4. Build response (no token)
        return new UserResponseDto(
                user.getId(),
                user.getCredentials().getUsername(),
                profileMapper.entityToDto(user.getProfile()),
                user.getRoles().stream()
                        .map(Role::getAuthority)
                        .collect(Collectors.toSet()),
                user.isActive(),
                user.isAdmin()
        );
    }
}