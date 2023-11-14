package com.futuresubject.admin.exporter;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelExporter extends AbstractExporter {
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelExporter() {
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine(String nameSheet, List<String> headers) {
        sheet = workbook.createSheet(nameSheet);
        XSSFRow row = sheet.createRow(0);

        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        cellStyle.setFont(font);
        for (int i = 0 ; i < headers.size() ; i++) {
            createCell(row, i, headers.get(i), cellStyle);
        }
    }

    private void createCell(XSSFRow row, int columnIndex, Object value, CellStyle style) {
        XSSFCell cell = row.createCell(columnIndex);
        sheet.autoSizeColumn(columnIndex);

        if(value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);
    }

    private void writeDataLines(List<List<String>> listObjects) {
        int rowIndex = 1;

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        cellStyle.setFont(font);

        for(List<String> stringList : listObjects) {
            XSSFRow row = sheet.createRow(rowIndex++);
            int columnIndex = 0;
            for (String val : stringList) {
                createCell(row, columnIndex++,val, cellStyle);
            }
        }
    }

    public void export(String nameSheet,List<String> headers,List<List<String>> listObjects, HttpServletResponse response) throws IOException, IOException {
        super.setResponseHeader(response, ".application/octet-stream", ".xlsx", "users_");
        writeHeaderLine(nameSheet,headers);
        writeDataLines(listObjects);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
