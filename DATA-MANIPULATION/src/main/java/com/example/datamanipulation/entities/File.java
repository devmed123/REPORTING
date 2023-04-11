package com.example.datamanipulation.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.*;
import javax.persistence.Column;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class File {
    @Id
    Long id;

    String name ;

    int bloc_id_debut;
    int bloc_in_fin;
    @DBRef
    @JsonManagedReference
    List<Bloc> blocs;
}
