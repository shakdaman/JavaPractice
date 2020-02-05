package com.foruswithus.excelSheet;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.*;


public class Main {
    private static String claimSfx;
    private static int claimNbr;
    private static double checkAmt;
    private static Date checkDate;
    final static String filePath = "C:\\Users\\shakd\\Desktop\\testing\\excel";

    public static void main(String[] args) {
        processFile();

    }

    public static void processFile() {
        try {
            ArrayList<ExcelRecord> excelRecords = new ArrayList<>();


            FileInputStream fileInputStream = new FileInputStream(new File(filePath + ".xls"));

            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.rowIterator();
            rowIterator.next();
            while ( rowIterator.hasNext() ) {
                HSSFRow row = (HSSFRow) rowIterator.next();

                for ( int i = 0; i < row.getLastCellNum(); i ++ ) {
                    HSSFCell cell = row.getCell(i);

                    //System.out.println("\nCell #: " + row.getRowNum() + "_" + cell.getColumnIndex() );

                    switch ( i ) {
                        case 0:
                            System.out.println( "Claim Number: " + cell.getNumericCellValue() );
                            claimNbr = (int)cell.getNumericCellValue();
                            break;
                        case 1:
                            System.out.println( "Claim Suffix: " + cell.getStringCellValue() );
                            claimSfx = cell.getStringCellValue();
                            break;
                        case 2:
                            System.out.println( "Check Amount: " + cell.getNumericCellValue() );
                            checkAmt = cell.getNumericCellValue();
                            break;
                        case 3:
                            System.out.println( "Check Date: " + cell.getDateCellValue() );
                            checkDate = cell.getDateCellValue();
                            break;
                        default:
                            break;
                    }
                }
                excelRecords.add( new ExcelRecord(claimSfx, claimNbr, checkAmt, checkDate) );
            }

            System.out.println("Printing elements of ArrayList");
            for ( int i = 0 ; i < excelRecords.size(); i ++ ) {
                System.out.println( "Record " + (i + 1) + ": " + excelRecords.get(i).getClaimNbr() );
            }

            splitAndCreateSheets(excelRecords);
        } catch ( Exception e) {
            System.out.println( "Error Msg: " + e.getMessage() + "\nStack Trace: " + e.getStackTrace() );
        }
    }

    public static void splitAndCreateSheets(ArrayList arrayList) {
        int splitSize = arrayList.size() / 3;
        int sheetNbr = 1;

        for (int i = 0; i < arrayList.size(); i += splitSize) {
            List<ExcelRecord> chunk = arrayList.subList(i, Math.min(i + splitSize, arrayList.size()));
            System.out.println("Sublist has: " + chunk.size() + " elements.");

                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("Split_" + sheetNbr);
                HSSFRow row = sheet.createRow(0);

                row.createCell(0).setCellValue("CLAIM_NBR");
                row.createCell(1).setCellValue("CLAIM_SFX");
                row.createCell(2).setCellValue("CHECK_AMT");
                row.createCell(3).setCellValue("CHECK_DATE");


                for (int j = 1; j < chunk.size() - 1; j++) {
                    HSSFRow rows = sheet.createRow(j);
                    rows.createCell(0).setCellValue(chunk.get(j).getClaimNbr());
                    rows.createCell(1).setCellValue(chunk.get(j).getClaimSfx());
                    rows.createCell(2).setCellValue(chunk.get(j).getCheckAmt());
                    rows.createCell(3).setCellValue(chunk.get(j).getCheckDate());
                }
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath + sheetNbr + ".xls"));
                    workbook.write(fileOutputStream);

                } catch (Exception e) {
                    System.out.println("Error in splitAndCreateSheets()");
                }
            sheetNbr += 1;
        }

    }

}

