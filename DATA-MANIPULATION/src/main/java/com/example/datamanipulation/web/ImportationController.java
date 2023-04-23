package com.example.datamanipulation.web;

import com.example.datamanipulation.entities.*;
import com.example.datamanipulation.entities.File;
import com.example.datamanipulation.repositories.*;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.internal.MongoClientImpl;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/data")
public class ImportationController {

    @Autowired
     MongoClient mongoClient;
    @Autowired
    FileRepository fileRepository;
    @Autowired
    BlocRepository blocRepository;

    @PostMapping("store/{file_id}")
    public   List<String> upload(@RequestParam("file") MultipartFile multipartFile, @PathVariable int file_id) throws IOException {
        List<String> lines = new ArrayList<String>();
        InputStream inputStream = multipartFile.getInputStream();
        BufferedReader bufferReader =   new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferReader.readLine()) != null) {
            lines.add(line);
        }
        bufferReader.close();
        File f= fileRepository.findById((long) file_id).get();

             MongoDatabase db = mongoClient.getDatabase("ReportingData");
        List< InsertOneModel<Document>> list=new ArrayList< >();

       lines.forEach(e->{
           String  code=e.substring(f.getBloc_id_debut()-1,f.getBloc_in_fin()-1);
           Bloc b;
           if(f.getBlocs()
                   .stream()
                   .filter(bl -> bl.getCode().equals(Long.valueOf( code)))
                   .collect(Collectors.toList()).size()>0){
                b =f.getBlocs()
                       .stream()
                       .filter(bl -> bl.getCode().equals(Long.valueOf( code)))
                       .collect(Collectors.toList()).get(0);
               if(b!=null){
                   Document document = new Document();
                   document.append("bloc",b.getName());
                   for (Column c:
                           b.getColumns()) {
                       String  val=e.substring(c.getDebut()-1,c.getFin()-1);
                       document.append(c.getName(),val);
                   }
                   db.getCollection(b.getName()).insertOne(document);
                   InsertOneModel<Document> doc = new InsertOneModel<>(document);
                   list.add(doc);

               }
           }



       });
        for (Bloc  b:f.getBlocs()){
            List<WriteModel<Document>> bulkOperations = new ArrayList<>();
            list.stream().filter(e->e.getDocument().get("bloc").equals(b.getName())).forEach(e->bulkOperations.add(e));
            db.getCollection(b.getName()).bulkWrite(bulkOperations);
        }
       //
       return null;
    }








}
