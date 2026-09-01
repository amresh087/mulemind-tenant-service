package com.mulemind.tenant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TenantRequest {

    @NotBlank
    private String tenantCode;

    @NotBlank
    private String tenantName;
}
