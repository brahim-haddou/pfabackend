package com.example.pfabackend.model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {
    private static final String[] COLUMNs = {"Lundi", "Mardi", "Mercredi","Jeudi", "Vendredi", "Samedi"};
    public static ByteArrayInputStream customersToExcel(List<EmploiDuTemps> emploiDuTemps,List<Creneau> creneau) throws IOException {


        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ){
            Sheet sheet = workbook.createSheet(emploiDuTemps.get(0).getClasse().getElement().getModule().getFiliere().getNom());
            for (int i = 0; i < 12; i++) {
                sheet.createRow(i);
            }
            // Set up font
            Font font = workbook.createFont();
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setBold(true);
            // set up background color
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setFillBackgroundColor(IndexedColors.BLUE_GREY.getIndex());
            cellStyle.setFillPattern(FillPatternType.ALT_BARS);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            Row filRow = sheet.getRow(1);
            Cell cell = filRow.createCell(2);
            cell.setCellValue(emploiDuTemps.get(0).getClasse().getElement().getModule().getFiliere().getNom()+" Emplois Du Temps");
            cell.setCellStyle(cellStyle);
            //Merging cells by providing cell index
            sheet.addMergedRegion(new CellRangeAddress(1,1,2,creneau.size() + 1));


            // Set up font
            font = workbook.createFont();
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setBold(true);
            // set up background color
            cellStyle = workbook.createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setFillBackgroundColor(IndexedColors.BLUE.getIndex());
            cellStyle.setFillPattern(FillPatternType.ALT_BARS);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            int rowIdx = 2;
            Row headerRow = sheet.getRow(3);
            for (Creneau c: creneau) {
                headerRow.createCell(rowIdx).setCellValue(c.getDebut() +"H-"+c.getFin()+"H");
                headerRow.getCell(rowIdx).setCellStyle(cellStyle);
                sheet.setColumnWidth(rowIdx, 6000);
                rowIdx++;
            }

            // Set up font
            font = workbook.createFont();
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setBold(true);
            // set up foreground color
            cellStyle = workbook.createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            cellStyle.setFillPattern(FillPatternType.ALT_BARS);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            int col = 4;
            for (String c :COLUMNs) {
                Row row = sheet.getRow(col);
                row.createCell(1).setCellValue(c);
                row.getCell(1).setCellStyle(cellStyle);
                row.setHeightInPoints(100);
                col++;
            }

            // Set up font
            font = workbook.createFont();
            font.setColor(IndexedColors.BLACK.getIndex());
            font.setBold(true);
            // set up foreground color
            cellStyle = workbook.createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setWrapText(true);
            for(EmploiDuTemps e : emploiDuTemps){
                int[] idx = getCreneauIndeses(creneau, e.getCreneau());
                Row row =sheet.getRow(idx[0]);
                Cell cell1 =  row.createCell(idx[1]);
                cell1.setCellValue(
                        e.getClasse().getNom() + " || "+ e.getProfesseur().getNom() + "\n"+
                                "Salle : "+ e.getSalle().getNom() + "\n"
                );

                row.getCell(idx[1]).setCellStyle(cellStyle);
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
    private static int[] getCreneauIndeses(List<Creneau> creneau, Creneau cr){
        int ligIdx = 4;
        for(String j :COLUMNs){
            if(j.equals(cr.getJour())){
                break;
            }else{
                ligIdx++;
            }
        }
        int colIdx = 2;
        for(Creneau c: creneau){
            if(c.getDebut() == cr.getDebut()){
                break;
            }else{
                colIdx++;
            }
        }
        return new int[] { ligIdx, colIdx};
    }
}
