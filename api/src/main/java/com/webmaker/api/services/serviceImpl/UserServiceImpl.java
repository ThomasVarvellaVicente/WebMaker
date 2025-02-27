package com.webmaker.api.services.serviceImpl;

import com.webmaker.api.dtos.*;
import com.webmaker.api.entities.User;
import com.webmaker.api.exceptions.BadRequestException;
import com.webmaker.api.exceptions.NotFoundException;
import com.webmaker.api.mappers.CredentialsMapper;
import com.webmaker.api.mappers.ProfileMapper;
import com.webmaker.api.mappers.UserMapper;
import com.webmaker.api.repositories.UserRepository;
import com.webmaker.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CredentialsMapper credentialsMapper;
    private final ProfileMapper profileMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * Get user by username
     */
    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUser(String username) {
        return userMapper.entityToResponseDto(findUser(username));
    }

    /**
     * Update user profile
     */
    @Override
    @Transactional
    public UserResponseDto updateUserProfile(String username, ProfileDto profileDto) {
        User user = findUser(username);
        user.setProfile(profileMapper.dtoToEntity(profileDto));
        return userMapper.entityToResponseDto(userRepository.saveAndFlush(user));
    }

    /**
     * Update user credentials (username/password)
     */
    @Override
    @Transactional
    public UserResponseDto updateUserCredentials(String username, CredentialsDto credentialsDto) {
        credentialsErrorChecking(credentialsDto);
        User user = findUser(username);

        if (!Objects.equals(username, credentialsDto.getUsername()) && validateUsername(credentialsDto.getUsername())) {
            throw new BadRequestException("A user with the newly entered username already exists.");
        }

        user.setCredentials(credentialsMapper.dtoToEntity(credentialsDto));

        return userMapper.entityToResponseDto(userRepository.saveAndFlush(user));
    }

    /**
     * Update user's admin status
     */
    @Override
    @Transactional
    public UserResponseDto updateUserAdminStatus(String username, boolean adminStatus) {
        User user = findUser(username);
        user.setAdmin(adminStatus);
        return userMapper.entityToResponseDto(userRepository.saveAndFlush(user));
    }

    /**
     * Update user's active status
     */
    @Override
    @Transactional
    public UserResponseDto updateUserActiveStatus(String username, boolean activeStatus) {
        Optional<User> user = userRepository.findByCredentialsUsername(username);

        if (user.isEmpty()) {
            throw new NotFoundException("The username provided does not belong to an active user.");
        }

        user.get().setActive(activeStatus);

        return userMapper.entityToResponseDto(userRepository.saveAndFlush(user.get()));
    }

    /**
     * Helper method to validate credentials
     */
    private void credentialsErrorChecking(CredentialsDto credentialsDto) {
        if (credentialsDto == null
                || credentialsDto.getUsername() == null
                || credentialsDto.getPassword() == null) {
            throw new BadRequestException("A username and password are required.");
        }
    }

    /**
     * Helper method to check if a username already exists
     */
    private boolean validateUsername(String username) {
        return userRepository.findByCredentialsUsername(username).isPresent();
    }

    /**
     * Helper method to find a user by username (ensuring they are active)
     */
    private User findUser(String username) {
        Optional<User> user = userRepository.findByCredentialsUsernameAndActiveTrue(username);
        if (user.isEmpty()) {
            throw new NotFoundException("The username provided does not belong to an active user.");
        }
        return user.get();
    }
}