package com.example.export.application.dto;

public record ExportResult(
        String fileName,
        long rowCount
) {
}
