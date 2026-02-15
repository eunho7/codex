package com.example.export.application.policy;

import org.springframework.stereotype.Component;

@Component
public class DefaultExportFileNamePolicy implements ExportFileNamePolicy {
    @Override
    public String generate(String exportType) {
        return exportType + ".xlsx";
    }
}
