package com.example.certif.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class Montant {

    String N_doss;
    String N_utilisation;
    String montant_autorisation;
    String   montant_sous_autorisation;
    String montant_caution ;
    String montant_encours_global;
    String  montant_disponible_global;
    String  montant_GOD_restant;
    String  code_devise;

        public boolean egale(Montant montant){
        return (this.montant_autorisation.equals(montant.montant_autorisation))
                && this.montant_sous_autorisation.equals(montant.montant_sous_autorisation)
                && this.montant_caution.equals(montant.montant_caution)
                && this.montant_encours_global.equals(montant.montant_encours_global)
                && this.montant_disponible_global.equals(montant.montant_disponible_global)
                && this.montant_GOD_restant.equals(montant.montant_GOD_restant)
                && this.code_devise.equals(montant.code_devise)
                && this.getN_doss().equals(montant.N_doss)
                && this.getN_utilisation().equals(montant.N_utilisation);


    }
}
