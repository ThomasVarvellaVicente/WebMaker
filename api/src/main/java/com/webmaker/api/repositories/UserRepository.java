package com.webmaker.api.repositories;

import com.webmaker.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by username (used for authentication)
    Optional<User> findByCredentialsUsername(String username);

    // Check if a username exists
    boolean existsByCredentialsUsername(String username);

    // Check if an email exists (ensures unique email)
    boolean existsByProfileEmail(String email);

    Optional<User> findByCredentialsUsernameAndActiveTrue(String username);
}
