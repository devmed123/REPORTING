package com.example.datamanipulation.web;

import com.example.datamanipulation.entities.*;


import com.example.datamanipulation.repositories.BlocRepository;
import com.example.datamanipulation.repositories.ColumnRepository;
import com.example.datamanipulation.repositories.FileRepository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    @GetMapping("/all")
    public List<Bloc> blocs(){
        return blocRepository.findAll().stream().toList();
    }
    @Autowired
    private MongoClient mongoClient;
    @PostMapping("/save_bloc/{file_id}")
    public Bloc save_bloc(@RequestBody Bloc bloc , @PathVariable Long file_id){
        MongoDatabase db = mongoClient.getDatabase("ReportingData");
         db.createCollection(bloc.getName());
        File f=fileRepository.findById(file_id).get();
       Bloc b= blocRepository.save(bloc);
        f.getBlocs().add(b);
        fileRepository.save(f);
        return   blocRepository.save(b);
    }

}
