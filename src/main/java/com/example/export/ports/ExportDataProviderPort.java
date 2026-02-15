package com.example.export.ports;

import com.example.export.application.dto.ExportRequest;

public interface ExportDataProviderPort<T> {
    ExportDataCursor<T> openCursor(ExportRequest request);
}
