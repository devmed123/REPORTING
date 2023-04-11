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
import java.util.stream.Stream;

@SpringBootApplication
@EnableMongoRepositories
public class DataManipulationApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DataManipulationApplication.class, args);
	}
	@Autowired
	FileRepository fileRepository;

	@Autowired
	BlocRepository blocRepository;

	@Autowired
	ColumnRepository columnRepository;
	@Override
	public void run(String... args) throws Exception {
		/*Bloc bloc1=new Bloc(12L ,20L,null, new ArrayList<>());
		List<Bloc> blocs=new ArrayList<Bloc>();
		blocs.add(bloc1);

		File f=new File(100L, "psdd",20 , 22, blocs);
		bloc1.setFile(f);

		Column col1=new Column(1L, 1,2,bloc1 );
		Column col2=new Column(2L, 3,4,bloc1 );
		columnRepository.save(col1);
		columnRepository.save(col2);

		List<Column> columns=new ArrayList<Column>();

		columns.add(col1);
		columns.add(col2);
		bloc1.setColumns(columns);

		fileRepository.save(f);
		blocRepository.save(bloc1);
*/

	}
}
