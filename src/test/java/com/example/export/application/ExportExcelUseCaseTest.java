package com.example.export.application;

import com.example.export.application.dto.ExportRequest;
import com.example.export.application.dto.ExportResult;
import com.example.export.application.policy.ExportColumnPolicy;
import com.example.export.application.policy.ExportFileNamePolicy;
import com.example.export.infrastructure.data.InMemoryExportDataCursor;
import com.example.export.ports.ExportDataCursor;
import com.example.export.ports.ExportDataProviderPort;
import com.example.export.ports.ExporterPort;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ExportExcelUseCaseTest {

    @Test
    void exportTrans_returnsRowCountFromExporter() throws IOException {
        ExportDataProviderPort<Map<String, Object>> provider = request ->
                new InMemoryExportDataCursor<>(List.of(Map.of("id", 1), Map.of("id", 2), Map.of("id", 3)));

        ExporterPort exporter = new ExporterPort() {
            @Override
            public <T> ExportResult exportToExcel(ExportRequest request, ExportDataCursor<T> cursor, java.io.OutputStream outputStream) {
                long count = 0;
                while (cursor.hasNext()) {
                    cursor.next();
                    count++;
                }
                return new ExportResult("ignored.xlsx", count);
            }
        };

        ExportFileNamePolicy fileNamePolicy = exportType -> exportType + ".xlsx";
        ExportColumnPolicy columnPolicy = request -> List.of("id");

        ExportExcelUseCase useCase = new ExportExcelUseCase(provider, exporter, fileNamePolicy, columnPolicy);

        ExportResult result = useCase.exportTrans(new ByteArrayOutputStream());

        assertThat(result.rowCount()).isEqualTo(3);
        assertThat(result.fileName()).isEqualTo("trans.xlsx");
    }
}
