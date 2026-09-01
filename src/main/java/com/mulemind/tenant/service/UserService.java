package com.mulemind.tenant.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mulemind.tenant.dto.UserRequest;
import com.mulemind.tenant.dto.UserResponse;
import com.mulemind.tenant.entity.RoleEntity;
import com.mulemind.tenant.entity.User;
import com.mulemind.tenant.repository.RoleRepository;
import com.mulemind.tenant.repository.TenantRepository;
import com.mulemind.tenant.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final TenantRepository tenantRepository;

    public UserResponse create(UserRequest request) {
        RoleEntity role = resolveRole(request.getRoleId());
        Long tenantId = resolveTenantId(request.getTenantId(), request.getTenantCode());

        User user = User.builder()
                .tenantId(tenantId)
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .status(request.getStatus())
                .enabled(Boolean.TRUE.equals(request.getEnabled()))
                .accountNonLocked(Boolean.TRUE.equals(request.getAccountNonLocked()))
                .credentialsNonExpired(Boolean.TRUE.equals(request.getCredentialsNonExpired()))
                .accountNonExpired(Boolean.TRUE.equals(request.getAccountNonExpired()))
                .lastLogin(request.getLastLogin())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(role)
                .build();

        return map(repository.save(user));
    }

    public List<UserResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public UserResponse getById(Long id) {
        return map(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse update(Long id, UserRequest request) {

        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setTenantId(request.getTenantId());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setStatus(request.getStatus());
        user.setEnabled(Boolean.TRUE.equals(request.getEnabled()));
        user.setAccountNonLocked(Boolean.TRUE.equals(request.getAccountNonLocked()));
        user.setCredentialsNonExpired(Boolean.TRUE.equals(request.getCredentialsNonExpired()));
        user.setAccountNonExpired(Boolean.TRUE.equals(request.getAccountNonExpired()));
        user.setLastLogin(request.getLastLogin());
        user.setUpdatedAt(LocalDateTime.now());
        user.setRole(resolveRole(request.getRoleId()));

        return map(repository.save(user));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public UserResponse getByUsername(String username) {
        return map(repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    private RoleEntity resolveRole(Long roleId) {
        if (roleId == null) {
            return null;
        }
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    private Long resolveTenantId(Long tenantId, String tenantCode) {
        if (tenantId != null) {
            return tenantRepository.findById(tenantId)
                    .orElseThrow(() -> new RuntimeException("Tenant not found"))
                    .getTenantId();
        }

        if (tenantCode != null && !tenantCode.trim().isEmpty()) {
            return tenantRepository.findByTenantCode(tenantCode.trim())
                    .orElseThrow(() -> new RuntimeException("Tenant not found"))
                    .getTenantId();
        }

        throw new RuntimeException("Tenant id or tenant code is required");
    }

    private UserResponse map(User u) {
        return UserResponse.builder()
                .id(u.getId())
                .tenantId(u.getTenantId())
                .username(u.getUsername())
                .email(u.getEmail())
                .password(u.getPassword())
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .phone(u.getPhone())
                .status(u.getStatus())
                .enabled(u.getEnabled())
                .accountNonLocked(u.getAccountNonLocked())
                .credentialsNonExpired(u.getCredentialsNonExpired())
                .accountNonExpired(u.getAccountNonExpired())
                .lastLogin(u.getLastLogin())
                .createdAt(u.getCreatedAt())
                .updatedAt(u.getUpdatedAt())
                .roleId(u.getRole() != null ? u.getRole().getId() : null)
                .roleName(u.getRole() != null ? u.getRole().getRoleName() : null)
                .active(u.isActive())
                .build();
    }
}
