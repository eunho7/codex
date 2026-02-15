package com.example.export.infrastructure.data;

import com.example.export.application.dto.ExportRequest;
import com.example.export.ports.ExportDataCursor;
import com.example.export.ports.ExportDataProviderPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MyBatisTransExportDataProvider implements ExportDataProviderPort<Map<String, Object>> {

    private final TransExportMapper mapper;

    public MyBatisTransExportDataProvider(TransExportMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ExportDataCursor<Map<String, Object>> openCursor(ExportRequest request) {
        List<Map<String, Object>> dummyRows = List.of(
                Map.of("id", 1L, "amount", 1200, "status", "DONE"),
                Map.of("id", 2L, "amount", 3500, "status", "PENDING")
        );
        return new InMemoryExportDataCursor<>(dummyRows);
    }
}
