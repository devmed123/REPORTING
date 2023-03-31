package com.example.datamanipulation.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "files")
public class File {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name ;

    @OneToMany(mappedBy = "file")
    List<Bloc> blocs;
}
