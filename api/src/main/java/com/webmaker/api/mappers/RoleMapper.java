package com.webmaker.api.mappers;

import com.webmaker.api.entities.Role;
import com.webmaker.api.entities.RoleType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    /**
     * Converts a Role entity to a String (for example, the role name).
     */
    default String entityToDto(Role role) {
        if (role == null) {
            return null;
        }
        // Adapt this to however your Role entity stores its name.
        return String.valueOf(role.getName());
    }

    /**
     * Converts a String (role name) back into a Role entity.
     */
    default Role dtoToEntity(String roleName) {
        if (roleName == null) {
            return null;
        }
        // Create a new Role and set its name.
        Role role = new Role();
        role.setName(RoleType.valueOf(roleName));
        return role;
    }
}

