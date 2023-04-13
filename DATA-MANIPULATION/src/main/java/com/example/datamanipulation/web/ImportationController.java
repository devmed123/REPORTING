package com.example.datamanipulation.web;

import com.example.datamanipulation.entities.*;
import com.example.datamanipulation.entities.File;
import com.example.datamanipulation.repositories.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/data")
public class ImportationController {
    @Autowired
    FileRepository fileRepository;
    @Autowired
    BlocRepository blocRepository;

    @Autowired
    IdentificationsRepository identificationsRepository;

    @Autowired
    MontantRepository montantRepository;

    @Autowired
    DatesRepository datesRepository;

    @Autowired
    FinanceRepository financeRepository;

    @Autowired
    IdentificationRepositoryAwb identificationsRepositoryAwb;

    @Autowired
    MontantRepositoryAwb montantRepositoryAwb;

    @Autowired
    DatesRepositoryAwb datesRepositoryAwb;

    @Autowired
    FinanceRepositoryAwb financeRepositoryAwb;
    @PostMapping("store/{file_id}")

    public   List<String> upload(@RequestParam("file") MultipartFile multipartFile, @PathVariable int file_id) throws IOException {
        List<String> lines = new ArrayList<String>();
        InputStream inputStream = multipartFile.getInputStream();
        BufferedReader bufferReader =   new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferReader.readLine()) != null) {
            lines.add(line);
        }
        bufferReader.close();
        File f= fileRepository.findById((long) file_id).get();
        MongoClient mongo = new MongoClient( "localhost" , 27017 );

        MongoDatabase database = mongo.getDatabase("ReportinData");
        List<WriteModel<Document>> bulkOperations = new ArrayList<>();
       lines.forEach(e->{
           Map<String,String> data=new HashMap<>();
          String  code=e.substring(f.getBloc_id_debut()-1,f.getBloc_in_fin()-1);
           Bloc b =f. getBlocs()
                   .stream()
                   .filter(bl -> bl.getCode() == Long.valueOf(code))
                   .collect(Collectors.toList()).get(0);
           if(b!=null){
               Document document = new Document();
               for (Column c:
                    b.getColumns()) {
                   String  val=e.substring(c.getDebut()-1,c.getFin()-1);
                   document.append(c.getName(),val);
               }
               InsertOneModel<Document> doc = new InsertOneModel<>(document);
               bulkOperations.add(doc);
           }
       });
        database.getCollection("201").bulkWrite(bulkOperations);
       return null;
    }

    @PostMapping("store")
    public   Set<String> doss(@RequestParam("file") MultipartFile multipartFile) throws IOException {
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
        String donnees_cles;
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
        List<Identification> identificationsList=new ArrayList<Identification>();
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongo.getDatabase("ReportinData");
        List<WriteModel<Document>> bulkOperationsidentification = new ArrayList<>();
        List<WriteModel<Document>> bulkOperationsdates = new ArrayList<>();
        List<WriteModel<Document>> bulkOperationsmontants = new ArrayList<>();
        List<WriteModel<Document>> bulkOperationsfinance= new ArrayList<>();
        for (String id: ids) {

            String[]  table=id.split("split");
           mydata =   data.stream().filter(e->e.substring(26,60).equals(table[0]) && e.substring(61,96).equals(table[1]) && e.substring(96,131).equals(table[2])).collect(Collectors.toList());


           // donnée identification
             donnees_cles=table[0]+" "+table[1]+" "+table[2];
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
             identificationsRepository.save(new Identification(null,donnees_cles,n_compte_client,reference_externe_utilisation,Code_produit,Code_agence,Code_client,reference_tiers_intervenant,identifiant_nouveau_beneficiaire,numero_LCN,lieu_delivrance,Lieu_paiement_obligation_cautionnée,reference_beneficiaire_preparametre));
             identificationsRepositoryAwb.save(new Identification_awb(null,donnees_cles,n_compte_client,reference_externe_utilisation,Code_produit,Code_agence,Code_client,reference_tiers_intervenant,identifiant_nouveau_beneficiaire,numero_LCN,lieu_delivrance,Lieu_paiement_obligation_cautionnée,reference_beneficiaire_preparametre));
            /*Document d_ident=new Document();
            d_ident.append("donnees_cles" ,donnees_cles);
            d_ident.append("n_compte_client", n_compte_client);
            d_ident.append("reference_externe_utilisation", reference_externe_utilisation);
            d_ident.append("Code_produit" ,Code_produit);
            d_ident.append("Code_agence" ,Code_agence);
            d_ident.append( "Code_client" ,Code_client);
            d_ident.append( "reference_tiers_intervenant" , reference_tiers_intervenant);
            d_ident.append( "identifiant_nouveau_beneficiaire" ,identifiant_nouveau_beneficiaire);
            d_ident.append( "numero_LCN", numero_LCN);
            d_ident.append( "lieu_delivrance" ,lieu_delivrance);
            d_ident.append( "Lieu_paiement_obligation_cautionnée" ,Lieu_paiement_obligation_cautionnée);
            d_ident.append( "reference_beneficiaire_preparametre",reference_beneficiaire_preparametre);
            InsertOneModel<Document> doc_ident = new InsertOneModel<>(d_ident);
            bulkOperationsidentification.add(doc_ident);
            */



           //dates
            Document d_date=new Document();
            date_envoie_offre=(data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).get(0).substring(256,266):"";
            date_début_utilisation=(data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).get(0).substring(   372,382):"";
            date_fin_utilisation=(data.stream().filter(e->e.substring(134,136).equals("31")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("31")).collect(Collectors.toList()).get(0).substring(   263,273):"";
            date_échéance_fictive=(data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("CO")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("CO")).collect(Collectors.toList()).get(0).substring(235,245):"";
            date_émission_garantie=(data.stream().filter(e->e.substring(134,136).equals("37")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("37")).collect(Collectors.toList()).get(0).substring(     284,293):"";
            date_première_perception_accessoire=(data.stream().filter(e->e.substring(134,136).equals("11") && e.substring(229,234).equals("COCAU")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("11") && e.substring(229,234).equals("COCAU")).collect(Collectors.toList()).get(0).substring(275,285):"";
            date_demande_caution=(data.stream().filter(e->e.substring(134,136).equals("37")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("37")).collect(Collectors.toList()).get(0).substring(     273,283):"";

            datesRepository.save(new Date_data(null,donnees_cles, date_envoie_offre,date_début_utilisation,date_fin_utilisation, date_échéance_fictive,date_émission_garantie,date_première_perception_accessoire, "",date_demande_caution));
            datesRepositoryAwb.save(new Date_data_awb(null,donnees_cles, date_envoie_offre,date_début_utilisation,date_fin_utilisation, date_échéance_fictive,date_émission_garantie,date_première_perception_accessoire, "",date_demande_caution));
        /*    d_date.append("donnees_cles" ,donnees_cles);
            d_date.append("date_envoie_offre",date_envoie_offre);
            d_date.append("date_début_utilisation",date_début_utilisation);
            d_date.append("date_fin_utilisation",date_fin_utilisation);
            d_date.append("date_échéance_fictive",date_échéance_fictive);
            d_date.append("date_émission_garantie",date_émission_garantie);
            d_date.append("date_première_perception_accessoire",date_première_perception_accessoire);
            d_date.append("date_demande_caution",date_demande_caution);

            InsertOneModel<Document> doc_date = new InsertOneModel<>(d_date);
            bulkOperationsdates.add(doc_date);

            */


            Document d_montant=new Document();
            montant_autorisation=(data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).get(0).substring(238,256):"";
            montant_sous_autorisation=(data.stream().filter(e->e.substring(134,136).equals("31")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("31")).collect(Collectors.toList()).get(0).substring(225,243):"";
            montant_caution=(data.stream().filter(e->e.substring(134,136).equals("37")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("37")).collect(Collectors.toList()).get(0).substring(239,257):"";
            montant_encours_global=(data.stream().filter(e->e.substring(134,136).equals("50")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("50")).collect(Collectors.toList()).get(0).substring(242,260):"";
            montant_disponible_global=(data.stream().filter(e->e.substring(134,136).equals("51")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("51")).collect(Collectors.toList()).get(0).substring(270,288):"";
            code_devise=(data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).size()>0) ? data.stream().filter(e->e.substring(134,136).equals("30")).collect(Collectors.toList()).get(0).substring(279,282):"";
            montant_GOD_restant=(data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("GD")).collect(Collectors.toList()).size()>0) ?data.stream().filter(e->e.substring(134,136).equals("05") && e.substring(223,225).equals("GD")).collect(Collectors.toList()).get(0).substring(284,299):"";
            montantRepository.save(new Montant(null, donnees_cles,montant_autorisation,montant_sous_autorisation,montant_caution,montant_encours_global,montant_disponible_global, code_devise, montant_GOD_restant));
            montantRepositoryAwb.save(new Montant_awb(null, donnees_cles,montant_autorisation,montant_sous_autorisation,montant_caution,montant_encours_global,montant_disponible_global, code_devise, montant_GOD_restant));
           /* d_montant.append("donnees_cles" ,donnees_cles);
            d_montant.append("montant_autorisation",montant_autorisation);
            d_montant.append("montant_sous_autorisation",montant_sous_autorisation);
            d_montant.append("montant_caution",montant_caution);
            d_montant.append("montant_encours_global",montant_encours_global);
            d_montant.append("montant_disponible_global",montant_disponible_global);
            d_montant.append("code_devise",code_devise);
            d_montant.append("montant_GOD_restant",montant_GOD_restant);

            InsertOneModel<Document> doc_montants = new InsertOneModel<>(d_montant);
            bulkOperationsmontants.add(doc_montants);
*/



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
      /*  database.getCollection("identifications").bulkWrite(bulkOperationsidentification);
        database.getCollection("dates").bulkWrite(bulkOperationsdates);
        database.getCollection("montants").bulkWrite(bulkOperationsmontants);
        database.getCollection("financiere").bulkWrite(bulkOperationsfinance);
        bufferReader.close();*/
        return ids;
    }






}
