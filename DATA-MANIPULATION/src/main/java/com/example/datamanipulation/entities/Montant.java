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
public class Montant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String donnees_cles;
    String montant_autorisation;
    String   montant_sous_autorisation;
    String montant_caution ;
    String montant_encours_global;
    String  montant_disponible_global;
    String  montant_GOD_restant;
    String  code_devise;
}
