package com.example.export.infrastructure.exporter;

import com.example.export.application.dto.ExportRequest;
import com.example.export.application.dto.ExportResult;
import com.example.export.ports.ExportDataCursor;
import com.example.export.ports.ExporterPort;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Component
public class PoiExcelExporter implements ExporterPort {

    @Override
    public <T> ExportResult exportToExcel(ExportRequest request, ExportDataCursor<T> cursor, OutputStream outputStream) throws IOException {
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet(request.spec().sheetName());
            List<String> headers = request.spec().headers();

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers.get(i));
            }

            long rowCount = 0;
            int rowIdx = 1;
            while (cursor.hasNext()) {
                T next = cursor.next();
                Row row = sheet.createRow(rowIdx++);
                if (next instanceof Map<?, ?> rowMap) {
                    for (int col = 0; col < headers.size(); col++) {
                        String key = headers.get(col);
                        Object value = rowMap.get(key);
                        row.createCell(col).setCellValue(value == null ? "" : String.valueOf(value));
                    }
                } else {
                    row.createCell(0).setCellValue(String.valueOf(next));
                }
                rowCount++;
            }

            workbook.write(outputStream);
            workbook.dispose();
            return new ExportResult(request.exportType() + ".xlsx", rowCount);
        }
    }
}
