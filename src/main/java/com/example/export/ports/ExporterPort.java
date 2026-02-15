package com.example.export.ports;

import com.example.export.application.dto.ExportRequest;
import com.example.export.application.dto.ExportResult;

import java.io.IOException;
import java.io.OutputStream;

public interface ExporterPort {
    <T> ExportResult exportToExcel(ExportRequest request, ExportDataCursor<T> cursor, OutputStream outputStream) throws IOException;
}
