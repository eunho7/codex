package com.example.export.application.policy;

import com.example.export.application.dto.ExportRequest;

import java.util.List;

public interface ExportColumnPolicy {
    List<String> resolveHeaders(ExportRequest request);
}
