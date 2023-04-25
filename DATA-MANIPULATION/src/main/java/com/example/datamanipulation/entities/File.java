package com.example.datamanipulation.entities;

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
import java.util.ArrayList;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;

    String name ;

    int bloc_id_debut;
    int bloc_in_fin;
    @DBRef

    @OnDelete(action = OnDeleteAction.CASCADE)
    List<Bloc> blocs=new ArrayList<Bloc>();
}
