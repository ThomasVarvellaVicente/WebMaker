package com.webmaker.api.entities;

import com.webmaker.api.entities.Credentials;
import com.webmaker.api.entities.Profile;
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
@Table(name = "user_table")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Credentials credentials;

    @Embedded
    private Profile profile;

    @Column(nullable = false)
    private boolean active = true;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    // One-to-Many: A user can have multiple portfolios
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private Set<Portfolio> portfolios = new HashSet<>();

    // Many-to-Many: Users can collaborate on portfolios
    @ManyToMany
    @JoinTable(
            name = "user_collaborations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "portfolio_id")
    )
    @EqualsAndHashCode.Exclude
    private Set<Portfolio> collaborations = new HashSet<>();
}