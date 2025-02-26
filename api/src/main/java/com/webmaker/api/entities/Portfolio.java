package com.webmaker.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "portfolio")
@NoArgsConstructor
@Data
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private boolean isPublic = true;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    // Many-to-One: Each portfolio has one owner
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    // Many-to-Many: Collaborators (other users who can edit)
    @ManyToMany(mappedBy = "collaborations")
    @EqualsAndHashCode.Exclude
    private Set<User> collaborators = new HashSet<>();

    // One-to-Many: Portfolios can have multiple images
    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private Set<Image> images = new HashSet<>();

    // One-to-Many: Portfolios can have multiple videos
    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private Set<Video> videos = new HashSet<>();

    @Embedded
    private PortfolioSettings settings = new PortfolioSettings();
}
