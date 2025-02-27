package com.webmaker.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_table")
@NoArgsConstructor
@Data
public class User implements UserDetails {

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

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

    public boolean isAdmin() {
        return roles.stream().anyMatch(role -> role.getName() == RoleType.ROLE_ADMIN);
    }

    public void setAdmin(boolean isAdmin) {
        if (isAdmin) {
            this.roles.add(new Role(null, RoleType.ROLE_ADMIN)); // Add Role with RoleType.ROLE_ADMIN
        } else {
            this.roles.removeIf(role -> role.getName() == RoleType.ROLE_ADMIN); // Remove ADMIN role if exists
        }
    }

    // ✅ Implementing Spring Security's UserDetails Methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles; // Role implements GrantedAuthority
    }

    @Override
    public String getPassword() {
        return credentials.getPassword(); // Assuming Credentials has getPassword()
    }

    @Override
    public String getUsername() {
        return credentials.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}