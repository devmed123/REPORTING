package com.example.datamanipulation.repositories;

import com.example.datamanipulation.entities.Date_data;
import com.example.datamanipulation.entities.Date_data_awb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatesRepositoryAwb  extends JpaRepository<Date_data_awb,Long> {
}
