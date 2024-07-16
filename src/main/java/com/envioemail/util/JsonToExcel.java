package com.envioemail.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Component
public class JsonToExcel {

    public String convertJsonToExcel(String jsonString) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonArray = objectMapper.readTree(jsonString);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // Adicionar cabeçalhos (keys do JSON)
        if (jsonArray.isArray() && jsonArray.size() > 0) {
            JsonNode firstObject = jsonArray.get(0);
            Row headerRow = sheet.createRow(0);
            int headerIndex = 0;

            Iterator<Map.Entry<String, JsonNode>> fields = firstObject.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                headerRow.createCell(headerIndex++).setCellValue(field.getKey());
            }

            // Adicionar dados
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonNode jsonObject = jsonArray.get(i);
                Row dataRow = sheet.createRow(i + 1);
                int cellIndex = 0;

                fields = jsonObject.fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> field = fields.next();
                    dataRow.createCell(cellIndex++).setCellValue(field.getValue().asText());
                }
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream("./src/main/resources/arquivos/data.xlsx")) {
            workbook.write(fileOut);
        }

        workbook.close();

        return "\\arquivos\\data.xlsx";
    }

}