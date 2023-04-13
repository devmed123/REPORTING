package com.example.datamanipulation.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Identification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
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

}
