package com.example.datamanipulation.repositories;

import com.example.datamanipulation.entities.Identification;
import com.example.datamanipulation.entities.Identification_awb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentificationRepositoryAwb extends JpaRepository<Identification_awb,Long> {
}
