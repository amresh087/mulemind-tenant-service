package com.mulemind.tenant.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long id;
    private Long tenantId;
    private String username;
    private String email;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long roleId;
    private String roleName;
    private Boolean active;
}
