package com.mulemind.tenant.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mulemind.tenant.dto.TenantRequest;
import com.mulemind.tenant.dto.TenantResponse;
import com.mulemind.tenant.service.TenantService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService service;

    @PostMapping
    public TenantResponse create(@RequestBody @Valid TenantRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<TenantResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{tenantId}")
    public TenantResponse getById(@PathVariable Long tenantId) {
        return service.getById(tenantId);
    }

    @PutMapping("/{tenantId}")
    public TenantResponse update(@PathVariable Long tenantId, @RequestBody @Valid TenantRequest request) {
        return service.update(tenantId, request);
    }

    @DeleteMapping("/{tenantId}")
    public void delete(@PathVariable Long tenantId) {
        service.delete(tenantId);
    }
}
