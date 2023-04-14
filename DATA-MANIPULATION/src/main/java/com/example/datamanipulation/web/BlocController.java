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
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=" + "identification" + ".xlsx");
        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();

        Sheet sheet = workbook.createSheet("Table Data");

        // Header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("donnees_cles");
        headerRow.createCell(1).setCellValue("n_compte_client");
        headerRow.createCell(2).setCellValue("reference_externe_utilisation");
        headerRow.createCell(3).setCellValue("Code_produit");
        headerRow.createCell(4).setCellValue("Code_agence");
        headerRow.createCell(5).setCellValue("Code_client");
        headerRow.createCell(6).setCellValue("reference_tiers_intervenant");
        headerRow.createCell(7).setCellValue("identifiant_nouveau_beneficiaire");
        headerRow.createCell(8).setCellValue("numero_LCN");
        headerRow.createCell(9).setCellValue("lieu_delivrance");
        headerRow.createCell(10).setCellValue("Lieu_paiement_obligation_cautionnée");
        headerRow.createCell(11).setCellValue("reference_beneficiaire_preparametre");

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
        for (Identification identificationt : identificationList) {
            Row row = sheet.createRow(rowNumber++);
            System.out.println("test");
            row.createCell(0).setCellValue(identificationt.getDonnees_cles());
            row.createCell(1).setCellValue(identificationt.getN_compte_client());
            row.createCell(2).setCellValue(identificationt.getReference_externe_utilisation());
            row.createCell(3).setCellValue(identificationt.getCode_produit());
            row.createCell(4).setCellValue(identificationt.getCode_agence());
            row.createCell(5).setCellValue(identificationt.getCode_client());
            row.createCell(6).setCellValue(identificationt.getReference_tiers_intervenant());
            row.createCell(7).setCellValue(identificationt.getIdentifiant_nouveau_beneficiaire());
            row.createCell(8).setCellValue(identificationt.getNumero_LCN());
            row.createCell(9).setCellValue(identificationt.getLieu_delivrance());
            row.createCell(10).setCellValue(identificationt.getLieu_paiement_obligation_cautionnée());
            row.createCell(11).setCellValue(identificationt.getReference_beneficiaire_preparametre());
        }

        // Autosize columns
        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the workbook to response
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
