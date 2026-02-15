package com.example.export.application;

import com.example.export.application.dto.ExportRequest;
import com.example.export.application.dto.ExportResult;
import com.example.export.application.dto.ExportSpec;
import com.example.export.application.policy.ExportColumnPolicy;
import com.example.export.application.policy.ExportFileNamePolicy;
import com.example.export.ports.ExportDataCursor;
import com.example.export.ports.ExportDataProviderPort;
import com.example.export.ports.ExporterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExportExcelUseCase {

    private final ExportDataProviderPort<Map<String, Object>> dataProvider;
    private final ExporterPort exporter;
    private final ExportFileNamePolicy fileNamePolicy;
    private final ExportColumnPolicy columnPolicy;

    public ExportResult exportTrans(OutputStream outputStream) throws IOException {
        ExportRequest baseRequest = ExportRequest.builder()
                .exportType("trans")
                .spec(new ExportSpec("transactions", null))
                .conditions(Map.of())
                .build();

        ExportRequest request = ExportRequest.builder()
                .exportType(baseRequest.exportType())
                .spec(new ExportSpec(
                        baseRequest.spec().sheetName(),
                        columnPolicy.resolveHeaders(baseRequest)
                ))
                .conditions(baseRequest.conditions())
                .build();

        try (ExportDataCursor<Map<String, Object>> cursor = dataProvider.openCursor(request)) {
            ExportResult result = exporter.exportToExcel(request, cursor, outputStream);
            return new ExportResult(fileNamePolicy.generate(request.exportType()), result.rowCount());
        }
    }
}
