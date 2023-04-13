package com.example.datamanipulation.repositories;

import com.example.datamanipulation.entities.Date_data;
import com.example.datamanipulation.entities.Identification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatesRepository  extends JpaRepository<Date_data,Long> {
}
