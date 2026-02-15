package com.example.export.application.dto;

import lombok.Builder;

import java.util.Map;

@Builder
public record ExportRequest(
        String exportType,
        ExportSpec spec,
        Map<String, Object> conditions
) {
}
