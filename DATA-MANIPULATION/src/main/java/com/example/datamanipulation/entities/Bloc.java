package com.example.datamanipulation.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bloc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;
    @javax.persistence.Column(nullable = false, unique = true)
    String name ;

    @javax.persistence.Column(nullable = false)
    Long code;
    @DBRef
    @JsonBackReference
    File file;
    @DBRef
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<Column> columns=new ArrayList<Column>();
}
