package com.example.datamanipulation.web;

import com.example.datamanipulation.entities.Bloc;
import com.example.datamanipulation.entities.Column;

import com.example.datamanipulation.repositories.BlocRepository;
import com.example.datamanipulation.repositories.ColumnRepository;
import com.example.datamanipulation.repositories.FileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ColumnContoller {
    @Autowired
    FileRepository fileRepository;
    @Autowired
    ColumnRepository columnRepository;
    @Autowired
    BlocRepository blocRepository;
    @PostMapping("/save_column/{bloc_id}")
    public Column save_column(@RequestBody Column column , @PathVariable Long bloc_id){
        Bloc b=blocRepository.findById(bloc_id).get();
        b.getColumns().add(column);
        column.setBloc(b);
        blocRepository.save(b);
        return  columnRepository.save(column);
    }
}
