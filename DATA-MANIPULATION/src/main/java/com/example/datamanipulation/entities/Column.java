package com.example.datamanipulation.entities;

import javax.persistence.*;

@Entity
@Table(name = "columns")
public class Column {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    int debut;
    int fin;

    @ManyToOne
    Bloc bloc;

}
