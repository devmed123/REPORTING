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
public class Financiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String donnees_cles;
    String catégorie_sous_ligne;
    String   taux_caution;
    String     taux_calcul_commission;
}
