package com.gmail.kharchenko55.vlad.model.user;

import com.gmail.kharchenko55.vlad.common.BaseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
@Component
public class User extends BaseEntity {
    private String email;
    private String password;
    private UserRole userRole;
    private UserStatus userStatus;

    public User() {
    }

    @Email
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    public UserStatus getStatus() {
        return userStatus;
    }

    public void setStatus(UserStatus status) {
        this.userStatus = status;
    }

    @NotEmpty
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}