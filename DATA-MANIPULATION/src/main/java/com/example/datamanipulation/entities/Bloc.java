package com.example.datamanipulation.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "blocs")

public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    File file;

    @OneToMany(mappedBy = "bloc")
    List<Column> columns;
}
