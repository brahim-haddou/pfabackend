package com.example.pfabackend.model;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {
    private static final String[] COLUMNs = {"Lundi", "mercredi", "mardi","jeudi", "vendredi", "samedi", " "," ", " "};
    public static ByteArrayInputStream customersToExcel(List<EmploiDuTemps> emploiDuTemps,List<Creneau> creneau) throws IOException {


        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ){
            // TODO: add style to excel file
//            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet(emploiDuTemps.get(0).getClasse().getElement().getModule().getFiliere().getNom());

            int rowIdx = 2;
            Row headerRow = sheet.createRow(3);
            for (Creneau c: creneau) {
                headerRow.createCell(rowIdx).setCellValue(c.getDebut() +" - "+c.getFin());
                rowIdx++;
            }

            int col = 4;
            for (String c :COLUMNs) {
                Row row = sheet.createRow(col);
                System.out.println(c);
                row.createCell(1).setCellValue(c);
                col++;
            }
            for(EmploiDuTemps e : emploiDuTemps){
                int[] idx = getCreneauIndeses(creneau, e.getCreneau());
                Row row =sheet.getRow(idx[0]);
                row.createCell(idx[1]).setCellValue(
                        e.getClasse().getNom() + "\n"+
                        e.getClasse().getElement().getNom() + "\n"+
                        e.getProfesseur().getNom() + "\n"+
                        e.getSalle().getNom() + "\n"+
                        e.getSalle().getNom() + "\n"
                );
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
        System.out.println(ligIdx+" "+colIdx);
        return new int[] { ligIdx, colIdx};
    }
}
