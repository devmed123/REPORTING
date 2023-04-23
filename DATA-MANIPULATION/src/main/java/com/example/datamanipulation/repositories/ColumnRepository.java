package com.example.datamanipulation.repositories;
import com.example.datamanipulation.entities.Column;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ColumnRepository extends MongoRepository<Column,Long> {
}
