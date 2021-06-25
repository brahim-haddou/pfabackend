package com.example.pfabackend.model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {
    private static final String[] COLUMNs = {"Lundi", "mercredi", "mardi","jeudi", "vendredi", "samedi"};
    public static ByteArrayInputStream customersToExcel(List<EmploiDuTemps> emploiDuTemps,List<Creneau> creneau) throws IOException {


        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ){
            Sheet sheet = workbook.createSheet(emploiDuTemps.get(0).getClasse().getElement().getModule().getFiliere().getNom());
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

            Row filRow = sheet.createRow(1);
            Cell cell = filRow.createCell(2);
            cell.setCellValue("Two cells have merged");
            cell.setCellStyle(cellStyle);
            //Merging cells by providing cell index
            sheet.addMergedRegion(new CellRangeAddress(1,1,2,5));


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
            Row headerRow = sheet.createRow(3);
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
                Row row = sheet.createRow(col);
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
                row.createCell(idx[1]).setCellValue(
                        e.getClasse().getType() + " de "+ e.getClasse().getNom() + "\n"+
                        "Element : "+ e.getClasse().getElement().getNom() + "\n"+
                        "Professeur : "+ e.getProfesseur().getNom() + "\n"+
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
