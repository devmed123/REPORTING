package com.example.datamanipulation.repositories;

import com.example.datamanipulation.entities.Identification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentificationsRepository extends JpaRepository<Identification,Long> {
}
