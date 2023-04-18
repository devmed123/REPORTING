package com.example.certif.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class Date_data {

    String N_doss;
    String N_utilisation;
    String date_envoie_offre;
    String date_début_utilisation;
    String date_fin_utilisation;
    String date_échéance_fictive;
    String date_émission_garantie;
    String date_première_perception_accessoire;
    String date_mainlevée_partielle;
    String date_demande_caution;
    public boolean egale(Date_data dateData){
        return (this.date_envoie_offre.equals(dateData.date_envoie_offre))
                && this.date_début_utilisation.equals(dateData.date_début_utilisation)
                && this.date_fin_utilisation.equals(dateData.date_fin_utilisation)
                && this.date_échéance_fictive.equals(dateData.date_échéance_fictive)
                && this.date_émission_garantie.equals(dateData.date_émission_garantie)
                && this.date_première_perception_accessoire.equals(dateData.date_première_perception_accessoire)
                && this.date_mainlevée_partielle.equals(dateData.date_mainlevée_partielle)
                && this.date_demande_caution.equals(dateData.date_demande_caution)
                && this.getN_doss().equals(dateData.N_doss)
                && this.getN_utilisation().equals(dateData.N_utilisation);


    }
}
