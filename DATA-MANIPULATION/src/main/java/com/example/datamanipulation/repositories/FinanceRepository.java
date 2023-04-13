package com.example.datamanipulation.repositories;

import com.example.datamanipulation.entities.Financiere;
import com.example.datamanipulation.entities.Identification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepository  extends JpaRepository<Financiere,Long> {
}
