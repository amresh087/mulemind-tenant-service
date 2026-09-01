package com.mulemind.tenant.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mulemind.tenant.dto.TenantRequest;
import com.mulemind.tenant.dto.TenantResponse;
import com.mulemind.tenant.entity.Tenant;
import com.mulemind.tenant.repository.TenantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository repository;

    public TenantResponse create(TenantRequest request) {
        Tenant tenant = Tenant.builder()
                .tenantCode(request.getTenantCode())
                .tenantName(request.getTenantName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return map(repository.save(tenant));
    }

    public List<TenantResponse> getAll() {
        return repository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    public TenantResponse getById(Long tenantId) {
        return map(repository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found")));
    }

    public TenantResponse update(Long tenantId, TenantRequest request) {
        Tenant tenant = repository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        tenant.setTenantCode(request.getTenantCode());
        tenant.setTenantName(request.getTenantName());
        tenant.setUpdatedAt(LocalDateTime.now());

        return map(repository.save(tenant));
    }

    public void delete(Long tenantId) {
        repository.deleteById(tenantId);
    }

    private TenantResponse map(Tenant tenant) {
        return TenantResponse.builder()
                .tenantId(tenant.getTenantId())
                .tenantCode(tenant.getTenantCode())
                .tenantName(tenant.getTenantName())
                .createdAt(tenant.getCreatedAt())
                .updatedAt(tenant.getUpdatedAt())
                .build();
    }
}
