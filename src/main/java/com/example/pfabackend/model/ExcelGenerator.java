package com.example.pfabackend.model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.hibernate.annotations.Columns;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ExcelGenerator {
    private static String[] COLUMNs = {"Lundi", "mercredi", "mardi","jeudi", "vendredi", "samedi", " "," ", " "};
    public static ByteArrayInputStream customersToExcel(List<EmploiDuTemps> emploiDuTemps,List<Creneau> creneau) throws IOException {


        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Customers");

            // TODO: fixing the form of the excel file

            int rowIdx = 1;
            Row headerRow = sheet.createRow(3);
            for (Creneau c: creneau) {
                headerRow.createCell(rowIdx).setCellValue(c.getDebut() +" - "+c.getFin());
                rowIdx++;
            }

            int col = 0;
            for (String c :COLUMNs) {
                Row row = sheet.createRow(col+3);
                row.createCell(0).setCellValue(c);
            }
//            for(EmploiDuTemps e : emploiDuTemps){
//                int[] idx = getCreneauIndeses(creneau, e.getCreneau());
//                Row row =sheet.getRow(idx[0]);
//                row.createCell(idx[1]).setCellValue(
//                        e.getClasse().getNom() + "\n"+
//                        e.getClasse().getElement().getNom() + "\n"+
//                        e.getProfesseur().getNom() + "\n"+
//                        e.getSalle().getNom() + "\n"+
//                        e.getSalle().getNom() + "\n"
//                );
//            }
//            for (EmploiDuTemps e : emploiDuTemps) {
//                Row row = sheet.createRow(rowIdx++);
//
//                row.createCell(0).setCellValue(e.getId());
//                row.createCell(1).setCellValue(e.getClasse().getNom());
//                row.createCell(2).setCellValue(e.getProfesseur().getNom());
//                row.createCell(3).setCellValue(e.getSalle().getNom());
//            }
            // Row for Header


            // Header
//            for (int col = 0; col < COLUMNs.length; col++) {
//                Cell cell = headerRow.createCell(col);
//                cell.setCellValue(COLUMNs[col]);
//                cell.setCellStyle(headerCellStyle);
//            }
//
//            // CellStyle for Age
//            CellStyle ageCellStyle = workbook.createCellStyle();
//            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
//
//            int rowIdx = 1;
//            for (EmploiDuTemps e : emploiDuTemps) {
//                Row row = sheet.createRow(rowIdx++);
//
//                row.createCell(0).setCellValue(e.getId());
//                row.createCell(1).setCellValue(e.getClasse().getNom());
//                row.createCell(2).setCellValue(e.getProfesseur().getNom());
//                row.createCell(3).setCellValue(e.getSalle().getNom());
//            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    private static int[] getCreneauIndeses(List<Creneau> creneau, Creneau cr){
        int ligIdx = 4;
        for(String j :COLUMNs){
            if(!j.equals(cr.getJour())){
                break;
            }else{
                ligIdx++;
            }
        }
        int colIdx = 1;
        for(Creneau c: creneau){
            if(c.getDebut() != cr.getDebut()){
                break;
            }else{
                colIdx++;
            }
        }
        System.out.println(ligIdx+" "+colIdx);
        return new int[] { ligIdx, colIdx};
    }
}
