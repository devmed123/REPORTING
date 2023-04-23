package com.example.datamanipulation;


import com.example.datamanipulation.entities.Bloc;
import com.example.datamanipulation.entities.Column;
import com.example.datamanipulation.entities.File;
import com.example.datamanipulation.repositories.BlocRepository;
import com.example.datamanipulation.repositories.ColumnRepository;
import com.example.datamanipulation.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@EnableMongoRepositories
public class DataManipulationApplication  {

	public static void main(String[] args) {
		SpringApplication.run(DataManipulationApplication.class, args);
	}
	/*@Autowired
	FileRepository fileRepository;

	@Autowired
	BlocRepository blocRepository;

	@Autowired
	ColumnRepository columnRepository;

	@Override
	public void run(String... args) throws Exception {
		Bloc bloc1=new Bloc(null ,"xeeee",20L,null, new ArrayList<>());
		List<Bloc> blocs=new ArrayList<>();
		blocs.add(bloc1);

		File f=new File(null, "psdd",20 , 22, blocs);
		bloc1.setFile(f);

		Column col1=new Column(null,"xeeee", 1,2,bloc1 );
		Column col2=new Column(null,"xeeee", 3,4,bloc1 );
		columnRepository.save(col1);
		columnRepository.save(col2);

		List<Column> columns=new ArrayList<Column>();

		columns.add(col1);
		columns.add(col2);
		bloc1.setColumns(columns);

		fileRepository.save(f);
		blocRepository.save(bloc1);


	}
	*/

}
