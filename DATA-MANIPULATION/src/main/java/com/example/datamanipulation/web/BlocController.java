package com.example.datamanipulation.web;

import com.example.datamanipulation.entities.Bloc;


import com.example.datamanipulation.entities.Column;
import com.example.datamanipulation.entities.File;
import com.example.datamanipulation.entities.Identification;
import com.example.datamanipulation.repositories.BlocRepository;
import com.example.datamanipulation.repositories.ColumnRepository;
import com.example.datamanipulation.repositories.FileRepository;
import com.example.datamanipulation.repositories.IdentificationsRepository;
import com.mongodb.MongoClient;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.mapping.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mongodb.client.MongoDatabase;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blocs")
public class BlocController {
    @Autowired
    FileRepository fileRepository;
    @Autowired
    ColumnRepository columnRepository;
    @Autowired
    BlocRepository blocRepository;
    @PostMapping("/save_bloc/{file_id}")
    public Bloc save_bloc(@RequestBody Bloc bloc , @PathVariable Long file_id){

        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        //Connecting to the database
        MongoDatabase database = mongo.getDatabase("ReportinData");
        //Creating a collection
        database.createCollection(bloc.getId().toString());
        File f=fileRepository.findById(file_id).get();

        f.getBlocs().add(bloc);
        Column col1=new Column(1L,"nom", 1,2,null );
        Column col2=new Column(2L,"prenom", 3,4,null );
        columnRepository.save(col1);
        columnRepository.save(col2);

        List<Column> columns=new ArrayList<Column>();

        columns.add(col1);
        columns.add(col2);
        bloc.setColumns(columns);

        fileRepository.save(f);
        return   blocRepository.save(bloc);

    }
@Autowired
    IdentificationsRepository  identificationsRepository;
    @GetMapping("/export/table")
    public void exportTableToExcelidentificationvalide(HttpServletResponse response,    List<Identification> identificationList) throws Exception {

        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();


    }
 public void export_identification(HttpServletResponse response,    List<Identification> identificationListvalide , List<Identification> identificationListvalidenot) throws Exception {

     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-Disposition", "attachment; filename=" + "identifications" + ".xlsx");
     Workbook workbook = new XSSFWorkbook();
     CellStyle cellStyle2 = workbook.createCellStyle();
     cellStyle2.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
     cellStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
     CreationHelper createHelper = workbook.getCreationHelper();
   //--------------
     Sheet sheet3 = workbook.createSheet("dossier valide");
     Row headerRow3 = sheet3.createRow(0);
     headerRow3.createCell(0).setCellValue("numéro dossier");
     headerRow3.getCell(0).setCellStyle(cellStyle2);
     int rowNumber3 = 1;
     for (Identification identificationt : identificationListvalide) {
         Row row3 = sheet3.createRow(rowNumber3++);
         row3.createCell(0).setCellValue(identificationt.getDonnees_cles().split("ttt")[0]);
     }
     for (int i = 0; i <=1; i++) {
         sheet3.autoSizeColumn(i);
     }
    //----------------------

     //--------------
     Sheet sheet4 = workbook.createSheet("dossier non valide");
     Row headerRow4 = sheet4.createRow(0);
     headerRow4.createCell(0).setCellValue("numéro dossier");
     headerRow4.getCell(0).setCellStyle(cellStyle2);
     int rowNumber4 = 1;
     for (Identification identificationt : identificationListvalidenot) {
         Row row4 = sheet4.createRow(rowNumber4++);
         row4.createCell(0).setCellValue(identificationt.getDonnees_cles().split("ttt")[0]);

     }
     for (int i = 0; i <=1; i++) {
         sheet4.autoSizeColumn(i);
     }
     //----------------------

     Sheet sheet = workbook.createSheet("identification non valide");
     // Header row
     Row headerRow = sheet.createRow(0);

     headerRow.createCell(0).setCellValue("numéro dossier");
     headerRow.getCell(0).setCellStyle(cellStyle2);
     headerRow.createCell(1).setCellValue("numéro utilisation");
     headerRow.getCell(1).setCellStyle(cellStyle2);
     headerRow.createCell(2).setCellValue("n_compte_client");
     headerRow.getCell(2).setCellStyle(cellStyle2);
     headerRow.createCell(3).setCellValue("reference_externe_utilisation");
     headerRow.getCell(3).setCellStyle(cellStyle2);
     headerRow.createCell(4).setCellValue("Code_produit");
     headerRow.getCell(4).setCellStyle(cellStyle2);
     headerRow.createCell(5).setCellValue("Code_agence");
     headerRow.getCell(5).setCellStyle(cellStyle2);
     headerRow.createCell(6).setCellValue("Code_client");
     headerRow.getCell(6).setCellStyle(cellStyle2);
     headerRow.createCell(7).setCellValue("reference_tiers_intervenant");
     headerRow.getCell(7).setCellStyle(cellStyle2);
     headerRow.createCell(8).setCellValue("identifiant_nouveau_beneficiaire");
     headerRow.getCell(8).setCellStyle(cellStyle2);
     headerRow.createCell(9).setCellValue("numero_LCN");
     headerRow.getCell(9).setCellStyle(cellStyle2);
     headerRow.createCell(10).setCellValue("lieu_delivrance");
     headerRow.getCell(10).setCellStyle(cellStyle2);
     headerRow.createCell(11).setCellValue("Lieu_paiement_obligation_cautionnée");
     headerRow.getCell(11).setCellStyle(cellStyle2);
     headerRow.createCell(12).setCellValue("reference_beneficiaire_preparametre");
     headerRow.getCell(12).setCellStyle(cellStyle2);

     // Style for header row
     CellStyle headerCellStyle = workbook.createCellStyle();
     headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());

     Font headerFont = workbook.createFont();
     headerFont.setColor(IndexedColors.WHITE.getIndex());
     headerCellStyle.setFont(headerFont);

     // Apply style to header row
     headerRow.setRowStyle(headerCellStyle);

     // Data rows
     int rowNumber = 1;
     for (Identification identificationt : identificationListvalidenot) {
         Row row = sheet.createRow(rowNumber++);
         CellStyle cellStyle = workbook.createCellStyle();
         cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
         cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
         row.createCell(0).setCellValue(identificationt.getDonnees_cles().split("ttt")[0]);
         if(identificationt.getDonnees_cles().split("ttt").length==2){
             row.createCell(1).setCellValue(identificationt.getDonnees_cles().split("ttt")[1]);

         }

         row.createCell(2).setCellValue(identificationt.getN_compte_client());
         row.createCell(3).setCellValue(identificationt.getReference_externe_utilisation());
         row.createCell(4).setCellValue(identificationt.getCode_produit());
         row.createCell(5).setCellValue(identificationt.getCode_agence());
         row.createCell(6).setCellValue(identificationt.getCode_client());
         row.createCell(7).setCellValue(identificationt.getReference_tiers_intervenant());
         row.createCell(8).setCellValue(identificationt.getIdentifiant_nouveau_beneficiaire());
         row.createCell(9).setCellValue(identificationt.getNumero_LCN());
         row.createCell(10).setCellValue(identificationt.getLieu_delivrance());
         row.createCell(11).setCellValue(identificationt.getLieu_paiement_obligation_cautionnée());
         row.createCell(12).setCellValue(identificationt.getReference_beneficiaire_preparametre());

         if(identificationt.getN_compte_client().contains(",")) {
             row.getCell(2).setCellStyle(cellStyle);
         }
         if(identificationt.getReference_externe_utilisation().contains(",")) {
             row.getCell(3).setCellStyle(cellStyle);

         }
         if(identificationt.getCode_produit().contains(",")) {
             row.getCell(4).setCellStyle(cellStyle);

         }
         if(identificationt.getCode_agence().contains(",")) {
             row.getCell(5).setCellStyle(cellStyle);

         }
         if(identificationt.getCode_client().contains(",")) {
             row.getCell(6).setCellStyle(cellStyle);

         }
         if(identificationt.getReference_tiers_intervenant().contains(",")) {
             row.getCell(7).setCellStyle(cellStyle);

         }
         if(identificationt.getIdentifiant_nouveau_beneficiaire().contains(",")) {
             row.getCell(8).setCellStyle(cellStyle);

         }
         if(identificationt.getNumero_LCN().contains(",")) {
             row.getCell(9).setCellStyle(cellStyle);

         }
         if(identificationt.getLieu_delivrance().contains(",")) {
             row.getCell(10).setCellStyle(cellStyle);

         }
         if(identificationt.getLieu_paiement_obligation_cautionnée().contains(",")) {
             row.getCell(11).setCellStyle(cellStyle);

         }
         if(identificationt.getReference_beneficiaire_preparametre().contains(",")) {
             row.getCell(12).setCellStyle(cellStyle);

         }
     }

     // Autosize columns
     for (int i = 0; i < 11; i++) {
         sheet.autoSizeColumn(i);
     }
      //------------------------------------------
     Sheet sheet2 = workbook.createSheet("identification valide");

     // Header row
     Row headerRow2 = sheet2.createRow(0);
     headerRow2.createCell(0).setCellValue("numéro dossier");
     headerRow2.getCell(0).setCellStyle(cellStyle2);
     headerRow2.createCell(1).setCellValue("utilisation");
     headerRow2.getCell(1).setCellStyle(cellStyle2);
     headerRow2.createCell(2).setCellValue("n_compte_client");
     headerRow2.getCell(2).setCellStyle(cellStyle2);
     headerRow2.createCell(3).setCellValue("reference_externe_utilisation");
     headerRow2.getCell(3).setCellStyle(cellStyle2);
     headerRow2.createCell(4).setCellValue("Code_produit");
     headerRow2.getCell(4).setCellStyle(cellStyle2);
     headerRow2.createCell(5).setCellValue("Code_agence");
     headerRow2.getCell(5).setCellStyle(cellStyle2);
     headerRow2.createCell(6).setCellValue("Code_client");
     headerRow2.getCell(6).setCellStyle(cellStyle2);
     headerRow2.createCell(7).setCellValue("reference_tiers_intervenant");
     headerRow2.getCell(7).setCellStyle(cellStyle2);
     headerRow2.createCell(8).setCellValue("identifiant_nouveau_beneficiaire");
     headerRow2.getCell(8).setCellStyle(cellStyle2);
     headerRow2.createCell(9).setCellValue("numero_LCN");
     headerRow2.getCell(9).setCellStyle(cellStyle2);
     headerRow2.createCell(10).setCellValue("lieu_delivrance");
     headerRow2.getCell(10).setCellStyle(cellStyle2);
     headerRow2.createCell(11).setCellValue("Lieu_paiement_obligation_cautionnée");
     headerRow2.getCell(11).setCellStyle(cellStyle2);
     headerRow2.createCell(12).setCellValue("reference_beneficiaire_preparametre");
     headerRow2.getCell(12).setCellStyle(cellStyle2);
     // Style for header row
     CellStyle headerCellStyle2 = workbook.createCellStyle();
     headerCellStyle2.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());

     Font headerFont2 = workbook.createFont();
     headerFont2.setColor(IndexedColors.WHITE.getIndex());
     headerCellStyle2.setFont(headerFont);

     // Apply style to header row
     headerRow2.setRowStyle(headerCellStyle);

     // Data rows
     int rowNumber2 = 1;
     for (Identification identificationt : identificationListvalide) {
         Row row2 = sheet2.createRow(rowNumber2++);
         row2.createCell(0).setCellValue(identificationt.getDonnees_cles().split("ttt")[0]);
         if(identificationt.getDonnees_cles().split("ttt").length==2){
             row2.createCell(1).setCellValue(identificationt.getDonnees_cles().split("ttt")[1]);

         }
         row2.createCell(2).setCellValue(identificationt.getN_compte_client());
         row2.createCell(3).setCellValue(identificationt.getReference_externe_utilisation());
         row2.createCell(4).setCellValue(identificationt.getCode_produit());
         row2.createCell(5).setCellValue(identificationt.getCode_agence());
         row2.createCell(6).setCellValue(identificationt.getCode_client());
         row2.createCell(7).setCellValue(identificationt.getReference_tiers_intervenant());
         row2.createCell(8).setCellValue(identificationt.getIdentifiant_nouveau_beneficiaire());
         row2.createCell(9).setCellValue(identificationt.getNumero_LCN());
         row2.createCell(10).setCellValue(identificationt.getLieu_delivrance());
         row2.createCell(11).setCellValue(identificationt.getLieu_paiement_obligation_cautionnée());
         row2.createCell(12).setCellValue(identificationt.getReference_beneficiaire_preparametre());
     }

     // Autosize columns
     for (int i = 0; i < 11; i++) {
         sheet2.autoSizeColumn(i);
     }

     // Write the workbook to response
     workbook.write(response.getOutputStream());
     workbook.close();
 }

}
