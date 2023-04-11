package com.example.datamanipulation.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.*;

@Document
@NoArgsConstructor @AllArgsConstructor
@Data
public class Column {
    @Id
    Long id;

    String name ;
    int debut;
    int fin;

    @DBRef
    Bloc bloc;

}
