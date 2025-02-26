package com.webmaker.api.repositories;

import com.webmaker.api.entities.Video;
import com.webmaker.api.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    // Find all videos associated with a specific portfolio
    List<Video> findByPortfolio(Portfolio portfolio);
}