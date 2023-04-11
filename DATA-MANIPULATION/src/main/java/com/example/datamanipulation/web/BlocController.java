package com.example.datamanipulation.web;

import com.example.datamanipulation.entities.Bloc;


import com.example.datamanipulation.entities.Column;
import com.example.datamanipulation.entities.File;
import com.example.datamanipulation.repositories.BlocRepository;
import com.example.datamanipulation.repositories.ColumnRepository;
import com.example.datamanipulation.repositories.FileRepository;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blocs")
public class BlocController {
    @Autowired
    FileRepository fileRepository;
    @Autowired
    ColumnRepository columnRepository;
    @Autowired
    BlocRepository blocRepository;
    @PostMapping("/save_bloc/{file_id}")
    public Bloc save_bloc(@RequestBody Bloc bloc , @PathVariable Long file_id){

        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        //Connecting to the database
        MongoDatabase database = mongo.getDatabase("ReportinData");
        //Creating a collection
        database.createCollection(bloc.getId().toString());
        File f=fileRepository.findById(file_id).get();

        f.getBlocs().add(bloc);
        Column col1=new Column(1L,"nom", 1,2,null );
        Column col2=new Column(2L,"prenom", 3,4,null );
        columnRepository.save(col1);
        columnRepository.save(col2);

        List<Column> columns=new ArrayList<Column>();

        columns.add(col1);
        columns.add(col2);
        bloc.setColumns(columns);

        fileRepository.save(f);
        return   blocRepository.save(bloc);

    }

}
