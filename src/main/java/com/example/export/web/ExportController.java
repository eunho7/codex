package com.example.export.web;

import com.example.export.application.ExportExcelUseCase;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ExportController {

    private final ExportExcelUseCase exportExcelUseCase;

    @GetMapping("/exports/trans.xlsx")
    public void exportTransExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=trans.xlsx");
        exportExcelUseCase.exportTrans(response.getOutputStream());
    }
}
