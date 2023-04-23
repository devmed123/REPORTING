package com.example.datamanipulation.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.*;

@Document
@NoArgsConstructor @AllArgsConstructor
@Data
public class Column {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;
    String name ;
    int debut;
    int fin;
    @DBRef
    @JsonBackReference
    Bloc bloc;

}
