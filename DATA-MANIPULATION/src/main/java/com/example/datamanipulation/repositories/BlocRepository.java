package com.example.datamanipulation.repositories;

import com.example.datamanipulation.entities.Bloc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlocRepository extends CrudRepository<Bloc,Long> {

}
