package com.webmaker.api.repositories;

import com.webmaker.api.entities.Image;
import com.webmaker.api.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    // Find all images associated with a specific portfolio
    List<Image> findByPortfolio(Portfolio portfolio);
}
