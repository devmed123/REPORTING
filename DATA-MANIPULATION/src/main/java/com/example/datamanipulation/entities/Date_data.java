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
public class Date_data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String donnees_cles;
    String date_envoie_offre;
    String date_début_utilisation;
    String date_fin_utilisation;
    String date_échéance_fictive;
    String date_émission_garantie;
    String date_première_perception_accessoire;
    String date_mainlevée_partielle;
    String date_demande_caution;

}
