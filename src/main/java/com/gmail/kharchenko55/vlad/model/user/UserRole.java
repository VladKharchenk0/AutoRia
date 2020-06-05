package com.gmail.kharchenko55.vlad.model.user;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole {
    ROLE_ADMIN("ROLE_ADMIN"),
    USER("USER"),
    ROLE_NEWCOMER("ROLE_NEWCOMER");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static Optional<UserRole> getUserRole(String status) {
        return Arrays.stream(UserRole.values())
                .filter(enumValue -> enumValue.getRole().equals(status))
                .findAny();
    }
}