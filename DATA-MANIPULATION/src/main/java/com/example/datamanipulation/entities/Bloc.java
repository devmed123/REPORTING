package com.example.datamanipulation.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bloc {
    @Id
    Long id;
    String name ;
    Long code;

    @DBRef
    @JsonBackReference
    File file;

    @DBRef
    List<Column> columns;
}
