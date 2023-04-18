package com.example.certif.Utils;

import com.example.certif.entities.Date_data;
import com.example.certif.entities.Identification;

import com.example.certif.entities.Montant;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Export {
    public void export(HttpServletResponse response, List<Identification> identificationListvalide , List<Identification> identificationListvalidenot , List<Date_data> dateDataListvalid , List<Date_data> dateDataListnotvalid , List<Montant> montantListvalid, List<Montant> montantListnotvalid) throws Exception {

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
            row3.createCell(0).setCellValue(identificationt.getN_doss());
        }
        for (int i = 0; i <= 1; i++) {
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
            row4.createCell(0).setCellValue(identificationt.getN_doss());

        }
        for (int i = 0; i <= 1; i++) {
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
            row.createCell(0).setCellValue(identificationt.getN_doss());
            row.createCell(1).setCellValue(identificationt.getN_utilisation());
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

            if (identificationt.getN_compte_client().contains(",")) {
                row.getCell(2).setCellStyle(cellStyle);
            }
            if (identificationt.getReference_externe_utilisation().contains(",")) {
                row.getCell(3).setCellStyle(cellStyle);

            }
            if (identificationt.getCode_produit().contains(",")) {
                row.getCell(4).setCellStyle(cellStyle);

            }
            if (identificationt.getCode_agence().contains(",")) {
                row.getCell(5).setCellStyle(cellStyle);

            }
            if (identificationt.getCode_client().contains(",")) {
                row.getCell(6).setCellStyle(cellStyle);

            }
            if (identificationt.getReference_tiers_intervenant().contains(",")) {
                row.getCell(7).setCellStyle(cellStyle);

            }
            if (identificationt.getIdentifiant_nouveau_beneficiaire().contains(",")) {
                row.getCell(8).setCellStyle(cellStyle);

            }
            if (identificationt.getNumero_LCN().contains(",")) {
                row.getCell(9).setCellStyle(cellStyle);

            }
            if (identificationt.getLieu_delivrance().contains(",")) {
                row.getCell(10).setCellStyle(cellStyle);

            }
            if (identificationt.getLieu_paiement_obligation_cautionnée().contains(",")) {
                row.getCell(11).setCellStyle(cellStyle);

            }
            if (identificationt.getReference_beneficiaire_preparametre().contains(",")) {
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
            row2.createCell(0).setCellValue(identificationt.getN_doss());
            row2.createCell(1).setCellValue(identificationt.getN_utilisation());
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


        Sheet sheet_date_nvalid = workbook.createSheet("date non valide");
        // Header row
        Row headerRow_date_nvalid = sheet_date_nvalid.createRow(0);

        headerRow_date_nvalid.createCell(0).setCellValue("numéro dossier");
        headerRow_date_nvalid.getCell(0).setCellStyle(cellStyle2);
        headerRow_date_nvalid.createCell(1).setCellValue("numéro utilisation");
        headerRow_date_nvalid.getCell(1).setCellStyle(cellStyle2);
        headerRow_date_nvalid.createCell(2).setCellValue("date_envoie_offre");
        headerRow_date_nvalid.getCell(2).setCellStyle(cellStyle2);
        headerRow_date_nvalid.createCell(3).setCellValue("date_début_utilisation");
        headerRow_date_nvalid.getCell(3).setCellStyle(cellStyle2);
        headerRow_date_nvalid.createCell(4).setCellValue("date_fin_utilisation");
        headerRow_date_nvalid.getCell(4).setCellStyle(cellStyle2);
        headerRow_date_nvalid.createCell(5).setCellValue("date_échéance_fictive");
        headerRow_date_nvalid.getCell(5).setCellStyle(cellStyle2);
        headerRow_date_nvalid.createCell(6).setCellValue("date_émission_garantie");
        headerRow_date_nvalid.getCell(6).setCellStyle(cellStyle2);
        headerRow_date_nvalid.createCell(7).setCellValue("date_première_perception_accessoire");
        headerRow_date_nvalid.getCell(7).setCellStyle(cellStyle2);
        headerRow_date_nvalid.createCell(8).setCellValue("date_mainlevée_partielle");
        headerRow_date_nvalid.getCell(8).setCellStyle(cellStyle2);
        headerRow_date_nvalid.createCell(9).setCellValue("date_demande_caution");
        headerRow_date_nvalid.getCell(9).setCellStyle(cellStyle2);


        // Style for header row

        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());

        // Apply style to header row
        headerRow_date_nvalid.setRowStyle(headerCellStyle);

        // Data rows
        int rowNumber_date_nvalid = 1;
        for (Date_data date : dateDataListnotvalid) {
            Row row = sheet_date_nvalid.createRow(rowNumber_date_nvalid++);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            row.createCell(0).setCellValue(date.getN_doss());
            row.createCell(1).setCellValue(date.getN_utilisation());
            row.createCell(2).setCellValue(date.getDate_envoie_offre());
            row.createCell(3).setCellValue(date.getDate_début_utilisation());
            row.createCell(4).setCellValue(date.getDate_fin_utilisation());
            row.createCell(5).setCellValue(date.getDate_échéance_fictive());
            row.createCell(6).setCellValue(date.getDate_émission_garantie());
            row.createCell(7).setCellValue(date.getDate_première_perception_accessoire());
            row.createCell(8).setCellValue(date.getDate_mainlevée_partielle());
            row.createCell(9).setCellValue(date.getDate_demande_caution());


            if (date.getDate_envoie_offre().contains(",")) {
                row.getCell(2).setCellStyle(cellStyle);
            }
            if (date.getDate_début_utilisation().contains(",")) {
                row.getCell(3).setCellStyle(cellStyle);

            }
            if (date.getDate_fin_utilisation().contains(",")) {
                row.getCell(4).setCellStyle(cellStyle);

            }
            if (date.getDate_échéance_fictive().contains(",")) {
                row.getCell(5).setCellStyle(cellStyle);

            }
            if (date.getDate_émission_garantie().contains(",")) {
                row.getCell(6).setCellStyle(cellStyle);

            }
            if (date.getDate_première_perception_accessoire().contains(",")) {
                row.getCell(7).setCellStyle(cellStyle);

            }
            if (date.getDate_mainlevée_partielle().contains(",")) {
                row.getCell(8).setCellStyle(cellStyle);

            }
            if (date.getDate_demande_caution().contains(",")) {
                row.getCell(9).setCellStyle(cellStyle);

            }

        }

        // Autosize columns
        for (int i = 0; i < 11; i++) {
            sheet_date_nvalid.autoSizeColumn(i);
        }


        Sheet sheet_date_valid = workbook.createSheet("date valide");
        // Header row
        Row headerRow_date_valid = sheet_date_valid.createRow(0);

        headerRow_date_valid.createCell(0).setCellValue("numéro dossier");
        headerRow_date_valid.getCell(0).setCellStyle(cellStyle2);
        headerRow_date_valid.createCell(1).setCellValue("numéro utilisation");
        headerRow_date_valid.getCell(1).setCellStyle(cellStyle2);
        headerRow_date_valid.createCell(2).setCellValue("date_envoie_offre");
        headerRow_date_valid.getCell(2).setCellStyle(cellStyle2);
        headerRow_date_valid.createCell(3).setCellValue("date_début_utilisation");
        headerRow_date_valid.getCell(3).setCellStyle(cellStyle2);
        headerRow_date_valid.createCell(4).setCellValue("date_fin_utilisation");
        headerRow_date_valid.getCell(4).setCellStyle(cellStyle2);
        headerRow_date_valid.createCell(5).setCellValue("date_échéance_fictive");
        headerRow_date_valid.getCell(5).setCellStyle(cellStyle2);
        headerRow_date_valid.createCell(6).setCellValue("date_émission_garantie");
        headerRow_date_valid.getCell(6).setCellStyle(cellStyle2);
        headerRow_date_valid.createCell(7).setCellValue("date_première_perception_accessoire");
        headerRow_date_valid.getCell(7).setCellStyle(cellStyle2);
        headerRow_date_valid.createCell(8).setCellValue("date_mainlevée_partielle");
        headerRow_date_valid.getCell(8).setCellStyle(cellStyle2);
        headerRow_date_valid.createCell(9).setCellValue("date_demande_caution");
        headerRow_date_valid.getCell(9).setCellStyle(cellStyle2);


        // Data rows
        int rowNumber_date_valid = 1;
        for (Date_data date : dateDataListvalid) {
            Row row = sheet_date_valid.createRow(rowNumber_date_valid++);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            row.createCell(0).setCellValue(date.getN_doss());
            row.createCell(1).setCellValue(date.getN_utilisation());
            row.createCell(2).setCellValue(date.getDate_envoie_offre());
            row.createCell(3).setCellValue(date.getDate_début_utilisation());
            row.createCell(4).setCellValue(date.getDate_fin_utilisation());
            row.createCell(5).setCellValue(date.getDate_échéance_fictive());
            row.createCell(6).setCellValue(date.getDate_émission_garantie());
            row.createCell(7).setCellValue(date.getDate_première_perception_accessoire());
            row.createCell(8).setCellValue(date.getDate_mainlevée_partielle());
            row.createCell(9).setCellValue(date.getDate_demande_caution());
        }
        for (int i = 0; i < 11; i++) {
            sheet_date_valid.autoSizeColumn(i);
        }



        Sheet sheet_montant_valid = workbook.createSheet("montant valide");
        // Header row
        Row headerRow_montant_valid = sheet_montant_valid.createRow(0);

        headerRow_montant_valid.createCell(0).setCellValue("numéro dossier");
        headerRow_montant_valid.getCell(0).setCellStyle(cellStyle2);
        headerRow_montant_valid.createCell(1).setCellValue("numéro utilisation");
        headerRow_montant_valid.getCell(1).setCellStyle(cellStyle2);
        headerRow_montant_valid.createCell(2).setCellValue("montant autorisation");
        headerRow_montant_valid.getCell(2).setCellStyle(cellStyle2);
        headerRow_montant_valid.createCell(3).setCellValue("montant sous autorisation");
        headerRow_montant_valid.getCell(3).setCellStyle(cellStyle2);
        headerRow_montant_valid.createCell(4).setCellValue("montant caution");
        headerRow_montant_valid.getCell(4).setCellStyle(cellStyle2);
        headerRow_montant_valid.createCell(5).setCellValue("montant encours global");
        headerRow_montant_valid.getCell(5).setCellStyle(cellStyle2);
        headerRow_montant_valid.createCell(6).setCellValue("montant disponible global");
        headerRow_montant_valid.getCell(6).setCellStyle(cellStyle2);
        headerRow_montant_valid.createCell(7).setCellValue("montant GOD restant");
        headerRow_montant_valid.getCell(7).setCellStyle(cellStyle2);
        headerRow_montant_valid.createCell(8).setCellValue("code devise");
        headerRow_montant_valid.getCell(8).setCellStyle(cellStyle2);


        // Data rows
        int rowNumber_montant_valid = 1;
        for (Montant montant : montantListvalid) {
            Row row = sheet_montant_valid.createRow(rowNumber_montant_valid++);
            row.createCell(0).setCellValue(montant.getN_doss());
            row.createCell(1).setCellValue(montant.getN_utilisation());
            row.createCell(2).setCellValue(montant.getMontant_autorisation());
            row.createCell(3).setCellValue(montant.getMontant_sous_autorisation());
            row.createCell(4).setCellValue(montant.getMontant_caution());
            row.createCell(5).setCellValue(montant.getMontant_encours_global());
            row.createCell(6).setCellValue(montant.getMontant_disponible_global());
            row.createCell(7).setCellValue(montant.getMontant_GOD_restant());
            row.createCell(8).setCellValue(montant.getCode_devise());
        }
        for (int i = 0; i < 11; i++) {
            sheet_montant_valid.autoSizeColumn(i);
        }




        Sheet sheet_montant_nvalid = workbook.createSheet("montant non valide");
        // Header row
        Row headerRow_montant_nvalid = sheet_montant_nvalid.createRow(0);

        headerRow_montant_nvalid.createCell(0).setCellValue("numéro dossier");
        headerRow_montant_nvalid.getCell(0).setCellStyle(cellStyle2);
        headerRow_montant_nvalid.createCell(1).setCellValue("numéro utilisation");
        headerRow_montant_nvalid.getCell(1).setCellStyle(cellStyle2);
        headerRow_montant_nvalid.createCell(2).setCellValue("montant autorisation");
        headerRow_montant_nvalid.getCell(2).setCellStyle(cellStyle2);
        headerRow_montant_nvalid.createCell(3).setCellValue("montant sous autorisation");
        headerRow_montant_nvalid.getCell(3).setCellStyle(cellStyle2);
        headerRow_montant_nvalid.createCell(4).setCellValue("montant caution");
        headerRow_montant_nvalid.getCell(4).setCellStyle(cellStyle2);
        headerRow_montant_nvalid.createCell(5).setCellValue("montant encours global");
        headerRow_montant_nvalid.getCell(5).setCellStyle(cellStyle2);
        headerRow_montant_nvalid.createCell(6).setCellValue("montant disponible global");
        headerRow_montant_nvalid.getCell(6).setCellStyle(cellStyle2);
        headerRow_montant_nvalid.createCell(7).setCellValue("montant GOD restant");
        headerRow_montant_nvalid.getCell(7).setCellStyle(cellStyle2);
        headerRow_montant_nvalid.createCell(8).setCellValue("code devise");
        headerRow_montant_nvalid.getCell(8).setCellStyle(cellStyle2);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // Data rows
        int rowNumber_montant_nvalid = 1;
        for (Montant montant : montantListnotvalid) {
            Row row = sheet_montant_nvalid.createRow(rowNumber_montant_nvalid++);
            row.createCell(0).setCellValue(montant.getN_doss());

            row.createCell(1).setCellValue(montant.getN_utilisation());
            row.createCell(2).setCellValue(montant.getMontant_autorisation());
            if (montant.getMontant_autorisation().contains(",")) {
                row.getCell(2).setCellStyle(cellStyle);
            }
            row.createCell(3).setCellValue(montant.getMontant_sous_autorisation());
            if (montant.getMontant_sous_autorisation().contains(",")) {
                row.getCell(3).setCellStyle(cellStyle);
            }
            row.createCell(4).setCellValue(montant.getMontant_caution());
            if (montant.getMontant_caution().contains(",")) {
                row.getCell(4).setCellStyle(cellStyle);
            }
            row.createCell(5).setCellValue(montant.getMontant_encours_global());
            if (montant.getMontant_encours_global().contains(",")) {
                row.getCell(5).setCellStyle(cellStyle);
            }
            row.createCell(6).setCellValue(montant.getMontant_disponible_global());
            if (montant.getMontant_disponible_global().contains(",")) {
                row.getCell(6).setCellStyle(cellStyle);
            }
            row.createCell(7).setCellValue(montant.getMontant_GOD_restant());
            if (montant.getMontant_GOD_restant().contains(",")) {
                row.getCell(7).setCellStyle(cellStyle);
            }
            row.createCell(8).setCellValue(montant.getCode_devise());
            if (montant.getCode_devise().contains(",")) {
                row.getCell(8).setCellStyle(cellStyle);
            }
        }
        for (int i = 0; i < 11; i++) {
            sheet_montant_nvalid.autoSizeColumn(i);
        }
        // Write the workbook to response
        workbook.write(response.getOutputStream());
        workbook.close();
    }


    public  List<Identification> readExcelFile_identifications(InputStream inputStream) throws IOException {
        List<Identification> identificationList = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            String N_doss = row.getCell(0).getStringCellValue();
            String N_utilisation=  row.getCell(1).getStringCellValue();
            String N_compte_client = row.getCell(2).getStringCellValue();
            String Reference_externe_utilisation=  row.getCell(3).getStringCellValue();
            String Code_produit=  row.getCell(4).getStringCellValue();
            String Code_agence=  row.getCell(5).getStringCellValue();
            String Code_client=  row.getCell(6).getStringCellValue();
            String Reference_tiers_intervenant=  row.getCell(7).getStringCellValue();
            String Identifiant_nouveau_beneficiaire=  row.getCell(8).getStringCellValue();
            String Numero_LCN  =row.getCell(9).getStringCellValue();
            String Lieu_delivrance=  row.getCell(10).getStringCellValue();
            String  Lieu_paiement_obligation_cautionnée=  row.getCell(11).getStringCellValue();
            String Reference_beneficiaire_preparametre= row.getCell(12).getStringCellValue();
            identificationList.add(new Identification(N_doss ,N_utilisation,N_compte_client,Reference_externe_utilisation,Code_produit,Code_agence,Code_client,Reference_tiers_intervenant,Identifiant_nouveau_beneficiaire,Numero_LCN,Lieu_delivrance,Lieu_paiement_obligation_cautionnée,Reference_beneficiaire_preparametre));
        }

        workbook.close();
        inputStream.close();

        return identificationList;
    }



    public List<Date_data> readExcelFile_date(InputStream inputStream) throws IOException {
        List<Date_data> dateDataList = new ArrayList<>();
        Workbook workbook2 = new XSSFWorkbook(inputStream);
        Sheet sheet2 = workbook2.getSheetAt(0);

        for (Row row : sheet2) {
            String N_doss = row.getCell(0).getStringCellValue();
            String N_utilisation=  row.getCell(1).getStringCellValue();
            String date_envoie_offre = row.getCell(2).getStringCellValue();
            String date_début_utilisation=  row.getCell(3).getStringCellValue();
            String date_fin_utilisation=  row.getCell(4).getStringCellValue();
            String date_échéance_fictive=  row.getCell(5).getStringCellValue();
            String date_émission_garantie=  row.getCell(6).getStringCellValue();
            String date_première_perception_accessoire=  row.getCell(7).getStringCellValue();
            String date_mainlevée_partielle=  row.getCell(8).getStringCellValue();
            String date_demande_caution  =row.getCell(9).getStringCellValue();

            dateDataList.add(new Date_data(N_doss ,N_utilisation,date_envoie_offre,date_début_utilisation,date_fin_utilisation,date_échéance_fictive,date_émission_garantie,date_première_perception_accessoire,date_mainlevée_partielle,date_demande_caution));
        }

        workbook2.close();
        inputStream.close();

        return dateDataList;
    }




    public List<Montant> readExcelFile_montant(InputStream inputStream) throws IOException {
        List<Montant> montantList = new ArrayList<Montant>();
        Workbook workbook2 = new XSSFWorkbook(inputStream);
        Sheet sheet2 = workbook2.getSheetAt(0);

        for (Row row : sheet2) {

            String N_doss = row.getCell(0).toString();
            String N_utilisation=  row.getCell(1).toString();
            String montant_autorisation = row.getCell(2).toString();
            String montant_sous_autorisation=  row.getCell(3).toString();
            String montant_caution=  row.getCell(4).toString();
            String montant_encours_global=  row.getCell(5).toString();
            String montant_disponible_global=  row.getCell(6).toString();
            String code_devise=  row.getCell(7).toString();
            String montant_GOD_restant=  row.getCell(8).toString();

            montantList.add(new Montant(N_doss, N_utilisation,montant_autorisation,montant_sous_autorisation
                    ,montant_caution,montant_encours_global,montant_disponible_global,code_devise,montant_GOD_restant));
        }

        workbook2.close();
        inputStream.close();

        return montantList;
    }
}
