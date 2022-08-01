package com.demowebshop.utility;

import com.demowebshop.pages.HomePage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class ExcelUtility {
    public String readStringData(int i, int j, String sheetname) throws IOException, FileNotFoundException {
        String filepath = System.getProperty("user.dir") + "\\src\\main\\resources\\homepage.xlsx";
        int rowTotal;
        FileInputStream f = new FileInputStream(filepath);
        XSSFWorkbook w = new XSSFWorkbook(f);
        XSSFSheet s = w.getSheet(sheetname);
        Row r = s.getRow(i);
        Cell c = r.getCell(j);
        return c.getStringCellValue();

    }
    public String readSingleData(int i, int j, String filepath,String sheetname) throws IOException {
        File file = new File(filepath);
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet(sheetname);
        Row r = sheet.getRow(i);
        Cell c = r.getCell(j);
        DataFormatter formatter = new DataFormatter();
        String value = formatter.formatCellValue(sheet.getRow(i).getCell(j));
        return value;
    }
    public Object[][] readDataFromExcel(String filePath, String sheetName) throws IOException
    {
        String filepath = System.getProperty("user.dir") + File.separator + filePath;
        FileInputStream file = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int noOfRows = sheet.getPhysicalNumberOfRows();
        int noOfColumns = sheet.getRow(0).getLastCellNum();

        String[][] data = new String[noOfRows - 1][noOfColumns];
        for (int i = 0; i < noOfRows - 1; i++) {
            for (int j = 0; j < noOfColumns; j++) {
                DataFormatter formatter = new DataFormatter();
                data[i][j] = formatter.formatCellValue(sheet.getRow(i + 1).getCell(j));
            }
            System.out.println();
        }
        workbook.close();
        file.close();
        for (String[] dataArr : data) {
            System.out.println(Arrays.toString(dataArr));
        }
        return data;
    }
}
