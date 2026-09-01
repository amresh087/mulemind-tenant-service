package com.mulemind.tenant.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TenantResponse {
    private Long tenantId;
    private String tenantCode;
    private String tenantName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
