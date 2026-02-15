package com.example.export.application.dto;

import java.util.List;

public record ExportSpec(
        String sheetName,
        List<String> headers
) {
}
