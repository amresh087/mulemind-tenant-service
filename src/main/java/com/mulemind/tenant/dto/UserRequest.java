package com.mulemind.tenant.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    private Long tenantId;
    private String tenantCode;

    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    private String password;

    private String firstName;
    private String lastName;
    private String phone;
    private String status;
    private Boolean enabled;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean accountNonExpired;
    private LocalDateTime lastLogin;
    private Long roleId;
}
