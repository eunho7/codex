package com.example.export.application.policy;

import com.example.export.application.dto.ExportRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultExportColumnPolicy implements ExportColumnPolicy {
    @Override
    public List<String> resolveHeaders(ExportRequest request) {
        return List.of("id", "amount", "status");
    }
}
