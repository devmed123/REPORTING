package com.example.datamanipulation.repositories;

import com.example.datamanipulation.entities.Bloc;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface BlocRepository extends MongoRepository<Bloc,Long> {

    List<Bloc> findByCode(Long code_enregistrement);
}
