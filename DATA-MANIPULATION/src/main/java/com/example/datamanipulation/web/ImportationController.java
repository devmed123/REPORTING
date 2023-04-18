package com.example.datamanipulation.web;

import com.example.datamanipulation.entities.*;
import com.example.datamanipulation.entities.File;
import com.example.datamanipulation.repositories.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/data")
public class ImportationController {
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
        MongoClient mongo = new MongoClient( "localhost" , 27017 );

        MongoDatabase database = mongo.getDatabase("ReportinData");
        List<WriteModel<Document>> bulkOperations = new ArrayList<>();
       lines.forEach(e->{
           Map<String,String> data=new HashMap<>();
          String  code=e.substring(f.getBloc_id_debut()-1,f.getBloc_in_fin()-1);
           Bloc b =f. getBlocs()
                   .stream()
                   .filter(bl -> bl.getCode() == Long.valueOf(code))
                   .collect(Collectors.toList()).get(0);
           if(b!=null){
               Document document = new Document();
               for (Column c:
                    b.getColumns()) {
                   String  val=e.substring(c.getDebut()-1,c.getFin()-1);
                   document.append(c.getName(),val);
               }
               InsertOneModel<Document> doc = new InsertOneModel<>(document);
               bulkOperations.add(doc);
           }
       });
        database.getCollection("201").bulkWrite(bulkOperations);
       return null;
    }








}
