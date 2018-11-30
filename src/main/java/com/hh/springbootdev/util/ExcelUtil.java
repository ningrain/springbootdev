/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: ExcelUtil.java</p>
 *
 * @author jiangningning
 * @date 2018/11/30
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/11/30 Create
 */
package com.hh.springbootdev.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: ExcelUtil</p>
 * <p>Description: </p>
 * @author jiangningning
 */
public class ExcelUtil {

    public static String exportExcel(String fileName, String[] headers, List<Map<String, Object>> dataMap){

        try {
            SXSSFWorkbook workbook = new SXSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            // 表头
            Row row = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(headers[i]);
            }
            for (int i = 0; i < dataMap.size(); i++) {
                Map<String, Object> data = dataMap.get(i);
                Row row1 = sheet.createRow(i+1);
                for (int j = 0; j < headers.length; j++) {
                    Cell cell = row1.createCell(j);
                    cell.setCellValue(data.get(headers[j]).toString());
                }
            }
            FileOutputStream fos = new FileOutputStream(fileName);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public static void main(String[] args) {
        String fileName = "D:\\AAA.xlsx";
        String[] headers = new String[]{"姓名", "年龄"};
        Map<String, Object> dataMap1 = new HashMap<>();
        dataMap1.put("姓名", "小张");
        dataMap1.put("年龄", 27);
        Map<String, Object> dataMap2 = new HashMap<>();
        dataMap2.put("姓名", "小李");
        dataMap2.put("年龄", 28);
        List<Map<String, Object>> dataMap = new ArrayList<>();
        dataMap.add(dataMap1);
        dataMap.add(dataMap2);
        exportExcel(fileName, headers, dataMap);
    }

}
