package com.example.datamanipulation.repositories;
import com.example.datamanipulation.entities.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FileRepository extends MongoRepository<File,String> {
}
