package com.example.certif.web;


import com.example.certif.Utils.Export;
import com.example.certif.entities.Date_data;
import com.example.certif.entities.Identification;
import com.example.certif.entities.Montant;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CertifController {

    @PostMapping("/store")
    public   void doss(HttpServletResponse response, @RequestParam("file") MultipartFile multipartFile , @RequestParam("identification_file") MultipartFile identification_file , @RequestParam("date_file") MultipartFile date_file, @RequestParam("montant_file") MultipartFile montant_file) throws Exception {
        Set<String> ids = new HashSet<String>();
        List<String> data = new ArrayList<String>();
        InputStream inputStream = multipartFile.getInputStream();
        BufferedReader bufferReader =   new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferReader.readLine()) != null) {
            if(line.substring(131, 133).equals("12")){
                data.add(line);
                ids.add(line.substring(26,60)+"split"+line.substring(61,96)+"split"+line.substring(96,131));
            }
        }
        String n_compte_client;
        String reference_externe_utilisation;
        String  Code_produit;
        String  Code_agence;
        String  Code_client;
        String reference_tiers_intervenant;
        String  identifiant_nouveau_beneficiaire;
        String  numero_LCN;
        String lieu_delivrance;
        String Lieu_paiement_obligation_cautionnée;
        String reference_beneficiaire_preparametre;


        String date_envoie_offre;
        String date_début_utilisation;
        String date_fin_utilisation;
        String date_échéance_fictive;
        String date_émission_garantie;
        String date_première_perception_accessoire;
        String date_mainlevée_partielle;
        String date_demande_caution;




        String montant_autorisation;
        String   montant_sous_autorisation;
        String montant_caution ;
        String montant_encours_global;
        String  montant_disponible_global;
        String  montant_GOD_restant;
        String  code_devise;


        String catégorie_sous_ligne;
        String   taux_caution;
        String     taux_calcul_commission;

        List<String> mydata;
        List<Identification> identificationsListvalid=new ArrayList<Identification>();
        List<Identification> identificationsListnotvalid=new ArrayList<Identification>();

        List<Date_data> dateDataListvalid=new ArrayList<Date_data>();
        List<Date_data> dateDataListnotvalid=new ArrayList<Date_data>();



        List<Montant> montantListvalid=new ArrayList<Montant>();
        List<Montant> montantListnotvalid=new ArrayList<Montant>();
        Export blocController=new Export();

        InputStream inputStream1 = identification_file.getInputStream();
        InputStream inputStream2 = date_file.getInputStream();
        InputStream inputStream3 = montant_file.getInputStream();
        List<Identification> identificationList_awb= blocController.readExcelFile_identifications(inputStream1);
        List<Date_data> datelist_awb= blocController.readExcelFile_date(inputStream2);
        List<Montant> montantList= blocController.readExcelFile_montant(inputStream3);
        for (String id: ids) {

            String[]  table=id.split("split");
            mydata =   data.stream().filter(e->e.substring(26,60).equals(table[0]) && e.substring(61,96).equals(table[1]) && e.substring(96,131).equals(table[2])).collect(Collectors.toList());
            String dossier=table[0].replaceAll(" ","");
            String utilisation=table[2].replaceAll(dossier,"").replace(" ","");
            n_compte_client=(data.stream().filter(e->e.substring(134,136).equals("04")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("04")).collect(Collectors.toList()).get(0).substring(244,278):"";
            reference_externe_utilisation=(data.stream().filter(e->e.substring(134,136).equals("37")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("37")).collect(Collectors.toList()).get(0).substring(306,338):"";
            Code_produit=(data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).get(0).substring(223,228):"";
            Code_agence=(data.stream().filter(e->e.substring(134,136).equals("03")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("03")).collect(Collectors.toList()).get(0).substring(267,275):"";
            Code_client=(data.stream().filter(e->e.substring(134,136).equals("03")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("03")).collect(Collectors.toList()).get(0).substring(223,243):"";
            reference_tiers_intervenant=(data.stream().filter(e->e.substring(134,136).equals("18")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("18")).collect(Collectors.toList()).get(0).substring(223,243):"";
            identifiant_nouveau_beneficiaire=(data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("BF")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("BF")).collect(Collectors.toList()).get(0).substring(231,237):"";
            numero_LCN=(data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("AV")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("AV")).collect(Collectors.toList()).get(0).substring(268,278):"";
            lieu_delivrance=(data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("GD")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("GD")).collect(Collectors.toList()).get(0).substring(332,335):"";
            Lieu_paiement_obligation_cautionnée=(data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("GD")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("GD")).collect(Collectors.toList()).get(0).substring(335,338):"";
            reference_beneficiaire_preparametre=(data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("BF")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("BF")).collect(Collectors.toList()).get(0).substring(225,231):"";
            Identification  identification_awb;
            Identification identification=new Identification(dossier,utilisation,n_compte_client,reference_externe_utilisation,Code_produit,Code_agence,Code_client,reference_tiers_intervenant,identifiant_nouveau_beneficiaire,numero_LCN,lieu_delivrance,Lieu_paiement_obligation_cautionnée,reference_beneficiaire_preparametre);
            if(identificationList_awb.stream().filter(e->e.getN_doss().equals(identification.getN_doss()) && e.getN_utilisation().equals(identification.getN_utilisation())).collect(Collectors.toList()).size()>0){
                identification_awb=identificationList_awb.stream().filter(e->e.getN_doss().equals(identification.getN_doss()) && Objects.equals(e.getN_utilisation(), identification.getN_utilisation())).collect(Collectors.toList()).get(0);
                if(identification_awb.egale(identification)){
                    identificationsListvalid.add(identification);
                }
                else {
                    identificationsListnotvalid.add(
                            new Identification(
                                    identification.getN_doss(),
                                    identification.getN_utilisation(),
                                    (identification.getN_compte_client().equals(identification_awb.getN_compte_client())? identification.getN_compte_client() : "LS: "+ identification.getN_compte_client()+"  ,    ACCOR: "+identification_awb.getN_compte_client()),
                                    (identification.getReference_externe_utilisation().equals(identification_awb.getReference_externe_utilisation())? identification.getReference_externe_utilisation() :"LS: "+ identification.getReference_externe_utilisation()+",  ACCOR:"+identification_awb.getReference_externe_utilisation()),
                                    (identification.getCode_produit().equals(identification_awb.getCode_produit())? identification.getCode_produit() : "LS: "+identification.getCode_produit()+"    ,    ACCOR:   "+identification_awb.getCode_produit()),
                                    (identification.getCode_agence().equals(identification_awb.getCode_agence())? identification.getCode_agence() :"LS: "+ identification.getCode_agence()+"     ,      ACCOR:   "+identification_awb.getCode_agence()),
                                    (identification.getCode_client().equals(identification_awb.getCode_client())? identification.getCode_client() :"LS: " +identification.getCode_client()+"    ,     ACCOR:     "+identification_awb.getCode_client()),
                                    (identification.getReference_tiers_intervenant().equals(identification_awb.getReference_tiers_intervenant())? identification.getReference_tiers_intervenant() :"LS: "+ identification.getReference_tiers_intervenant()+"        ,    ACCOR:        "+identification_awb.getReference_tiers_intervenant()),
                                    (identification.getIdentifiant_nouveau_beneficiaire().equals(identification_awb.getIdentifiant_nouveau_beneficiaire())? identification.getIdentifiant_nouveau_beneficiaire() :"LS: "+ identification.getIdentifiant_nouveau_beneficiaire()+"           ,      ACCOR:     "+identification_awb.getIdentifiant_nouveau_beneficiaire()),
                                    (identification.getNumero_LCN().equals(identification_awb.getNumero_LCN())? identification.getNumero_LCN() :"LS: "+ identification.getNumero_LCN()+"         ,       ACCORD:    "+identification_awb.getNumero_LCN()),
                                    (identification.getLieu_delivrance().equals(identification_awb.getLieu_delivrance())? identification.getLieu_delivrance() : "LS: "+identification.getLieu_delivrance()+"       ,         ACCOR:  "+identification_awb.getLieu_delivrance()),
                                    (identification.getLieu_paiement_obligation_cautionnée().equals(identification_awb.getLieu_paiement_obligation_cautionnée())? identification.getLieu_paiement_obligation_cautionnée() : "LS: "+ identification.getLieu_paiement_obligation_cautionnée()+"       ,         ACCOR:  "+identification_awb.getLieu_paiement_obligation_cautionnée()),
                                    (identification.getReference_beneficiaire_preparametre().equals(identification_awb.getReference_beneficiaire_preparametre())? identification.getReference_beneficiaire_preparametre() :"LS: "+ identification.getReference_beneficiaire_preparametre()+"       ,         ACCOR:  "+identification_awb.getReference_beneficiaire_preparametre())
                            )
                    );
                }
            }


            //dates
            date_envoie_offre=(data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).get(0).substring(256,266):"";
            date_début_utilisation=(data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).get(0).substring(   372,382):"";
            date_fin_utilisation=(data.stream().filter(e->e.substring(134,136).equals("31")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("31")).collect(Collectors.toList()).get(0).substring(   263,273):"";
            date_échéance_fictive=(data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("CO")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("CO")).collect(Collectors.toList()).get(0).substring(235,245):"";
            date_émission_garantie=(data.stream().filter(e->e.substring(134,136).equals("37")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("37")).collect(Collectors.toList()).get(0).substring(     283,293):"";
            date_première_perception_accessoire=(data.stream().filter(e->e.substring(134,136).equals("11") && e.substring(229,234).equals("COCAU")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("11") && e.substring(229,234).equals("COCAU")).collect(Collectors.toList()).get(0).substring(275,285):"";
            date_demande_caution=(data.stream().filter(e->e.substring(134,136).equals("37")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("37")).collect(Collectors.toList()).get(0).substring(     273,283):"";
            Date_data date_awb;
            Date_data date=new Date_data(dossier,utilisation,date_envoie_offre,date_début_utilisation,date_fin_utilisation,date_échéance_fictive,date_émission_garantie,date_première_perception_accessoire,"",date_demande_caution);
            if(datelist_awb.stream().filter(e->e.getN_doss().equals(date.getN_doss()) && e.getN_utilisation().equals( date.getN_utilisation())).collect(Collectors.toList()).size()>0){
                date_awb=datelist_awb.stream().filter(e->e.getN_doss().equals(date.getN_doss()) && e.getN_utilisation().equals( date.getN_utilisation())).collect(Collectors.toList()).get(0);
                if(date_awb.egale(date)){
                    dateDataListvalid.add(date);
                }
                else {
                    dateDataListnotvalid.add(
                            new Date_data(
                                    date.getN_doss(),
                                    date.getN_utilisation(),
                                    (date.getDate_envoie_offre().equals(date_awb.getDate_envoie_offre())? date.getDate_envoie_offre() : "LS: "+ date.getDate_envoie_offre()+"  ,    ACCOR: "+date_awb.getDate_envoie_offre()),
                                    (date.getDate_début_utilisation().equals(date_awb.getDate_début_utilisation())? date.getDate_début_utilisation() :"LS: "+ date.getDate_début_utilisation()+",  ACCOR:"+date_awb.getDate_début_utilisation()),
                                    (date.getDate_fin_utilisation().equals(date_awb.getDate_fin_utilisation())? date.getDate_fin_utilisation() : "LS: "+date.getDate_fin_utilisation()+"    ,    ACCOR:   "+date_awb.getDate_fin_utilisation()),
                                    (date.getDate_échéance_fictive().equals(date_awb.getDate_échéance_fictive())? date.getDate_échéance_fictive() :"LS: "+ date.getDate_échéance_fictive()+"     ,      ACCOR:   "+date_awb.getDate_échéance_fictive()),
                                    (date.getDate_émission_garantie().equals(date_awb.getDate_émission_garantie())? date.getDate_émission_garantie() :"LS: "+ date.getDate_émission_garantie()+"     ,      ACCOR:   "+date_awb.getDate_émission_garantie()),
                                    (date.getDate_première_perception_accessoire().equals(date_awb.getDate_première_perception_accessoire())? date.getDate_première_perception_accessoire() :"LS: "+ date.getDate_première_perception_accessoire()+"     ,      ACCOR:   "+date_awb.getDate_première_perception_accessoire()),
                                    "",
                                    (date.getDate_demande_caution().equals(date_awb.getDate_demande_caution())? date.getDate_demande_caution() :"LS: "+ date.getDate_demande_caution()+"     ,      ACCOR:   "+date_awb.getDate_demande_caution())

                            )
                    );
                }
            }


            montant_autorisation=(data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).get(0).substring(238,256):"";
            montant_sous_autorisation=(data.stream().filter(e->e.substring(134,136).equals("31")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("31")).collect(Collectors.toList()).get(0).substring(225,243):"";
            montant_caution=(data.stream().filter(e->e.substring(134,136).equals("37")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("37")).collect(Collectors.toList()).get(0).substring(239,257):"";
            montant_encours_global=(data.stream().filter(e->e.substring(134,136).equals("50")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("50")).collect(Collectors.toList()).get(0).substring(242,260):"";
            montant_disponible_global=(data.stream().filter(e->e.substring(134,136).equals("51")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("51")).collect(Collectors.toList()).get(0).substring(270,288):"";
            code_devise=(data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).get(0).substring(279,282):"";
            montant_GOD_restant=(data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("GD")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("GD")).collect(Collectors.toList()).get(0).substring(284,299):"";
            Montant mt= new Montant(dossier, utilisation,montant_autorisation,montant_sous_autorisation
                    ,montant_caution,montant_encours_global,montant_disponible_global,montant_GOD_restant,code_devise);
            Montant mt_awb;
            if(montantList.stream().filter(e->e.getN_doss().equals(mt.getN_doss()) && e.getN_utilisation().equals( mt.getN_utilisation())).collect(Collectors.toList()).size()>0  ){
                mt_awb=montantList.stream().filter(e->e.getN_doss().equals(mt.getN_doss()) && e.getN_utilisation().equals( mt.getN_utilisation())).collect(Collectors.toList()).get(0);

                if(mt_awb.egale(mt)){
                    montantListvalid.add(mt);
                }
                else {
                    montantListnotvalid.add(
                            new Montant(
                                    mt.getN_doss(),
                                    mt.getN_utilisation(),
                                    (mt.getMontant_autorisation().equals(mt_awb.getMontant_autorisation())? mt.getMontant_autorisation() : "LS: "+ mt.getMontant_autorisation()+"  ,    ACCOR: "+mt_awb.getMontant_autorisation()),
                                    (mt.getMontant_sous_autorisation().equals(mt_awb.getMontant_sous_autorisation())? mt.getMontant_sous_autorisation() :"LS: "+ mt.getMontant_sous_autorisation()+",  ACCOR:"+mt_awb.getMontant_sous_autorisation()),
                                    (mt.getMontant_caution().equals(mt_awb.getMontant_caution())? mt.getMontant_caution() : "LS: "+mt.getMontant_caution()+"    ,    ACCOR:   "+mt_awb.getMontant_caution()),
                                    (mt.getMontant_encours_global().equals(mt_awb.getMontant_encours_global())? mt.getMontant_encours_global() :"LS: "+ mt.getMontant_encours_global()+"     ,      ACCOR:   "+mt_awb.getMontant_encours_global()),
                                    (mt.getMontant_disponible_global().equals(mt_awb.getMontant_disponible_global())? mt.getMontant_disponible_global() :"LS: "+ mt.getMontant_disponible_global()+"     ,      ACCOR:   "+mt_awb.getMontant_disponible_global()),
                                    (mt.getCode_devise().equals(mt_awb.getCode_devise())? mt.getCode_devise() :"LS: "+ mt.getCode_devise()+"     ,      ACCOR:   "+mt_awb.getCode_devise()),
                                    (mt.getMontant_GOD_restant().equals(mt_awb.getMontant_GOD_restant())? mt.getMontant_GOD_restant() :"LS: "+ mt.getMontant_GOD_restant()+"     ,      ACCOR:   "+mt_awb.getMontant_GOD_restant())

                            )
                    );
                }
            }

            /*// financiere
            Document d_fin=new Document();
            catégorie_sous_ligne=(data.stream().filter(e->e.substring(134,136).equals("31")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("31")).collect(Collectors.toList()).get(0).substring(281,286):"";
            taux_calcul_commission=(data.stream().filter(e->e.substring(134,136).equals("11")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("11")).collect(Collectors.toList()).get(0).substring(442,452):"";
            taux_caution=(data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("CO")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("CO")).collect(Collectors.toList()).get(0).substring(281,289):"";

            d_fin.append("donnees_cles" ,donnees_cles);
            d_fin.append("catégorie_sous_ligne",catégorie_sous_ligne);
            d_fin.append("taux_calcul_commission",taux_calcul_commission);
            d_fin.append("taux_caution",taux_caution);

            InsertOneModel<Document> doc_fin = new InsertOneModel<>(d_fin);
            bulkOperationsfinance.add(doc_fin);

*/

        }
        blocController.export(response,identificationsListvalid,identificationsListnotvalid ,dateDataListvalid,dateDataListnotvalid  , montantListvalid, montantListnotvalid);




    }
}
