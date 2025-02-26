package com.webmaker.api.repositories;

import com.webmaker.api.entities.Portfolio;
import com.webmaker.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    // Find all portfolios by a specific user
    List<Portfolio> findByOwner(User owner);

    // Find all public portfolios (for users who want to browse)
    List<Portfolio> findByIsPublicTrue();
}

