package com.example.datamanipulation.repositories;

import com.example.datamanipulation.entities.Montant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MontantRepository extends JpaRepository<Montant,Long> {
}
