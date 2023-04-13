package com.example.datamanipulation.repositories;

import com.example.datamanipulation.entities.Financiere;
import com.example.datamanipulation.entities.Financiere_awb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepositoryAwb  extends JpaRepository<Financiere_awb,Long> {
}
