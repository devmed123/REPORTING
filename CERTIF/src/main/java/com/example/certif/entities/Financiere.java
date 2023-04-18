package com.example.certif.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class Financiere {

    String donnees_cles;
    String catégorie_sous_ligne;
    String   taux_caution;
    String     taux_calcul_commission;
}
