package com.example.certif.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class Identification {

    String N_doss;
    String N_utilisation;
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
    public boolean egale(Identification ident){
        return (this.numero_LCN.equals(ident.numero_LCN))
                && this.reference_beneficiaire_preparametre.equals(ident.reference_beneficiaire_preparametre)
                && this.Lieu_paiement_obligation_cautionnée.equals(ident.Lieu_paiement_obligation_cautionnée)
                && this.lieu_delivrance.equals(ident.lieu_delivrance)
                && this.identifiant_nouveau_beneficiaire.equals(ident.identifiant_nouveau_beneficiaire)
                && this.reference_tiers_intervenant.equals(ident.reference_tiers_intervenant)
                && this.Code_client.equals(ident.Code_client)
                && this.Code_agence.equals(ident.Code_agence)
                && this.reference_externe_utilisation.equals(ident.reference_externe_utilisation)
                && this.Code_produit.equals(ident.Code_produit)
                && this.getN_doss().equals(ident.N_doss)
                && this.getN_utilisation().equals(ident.N_utilisation)
                && this.n_compte_client.equals(ident.n_compte_client);

    }
}
