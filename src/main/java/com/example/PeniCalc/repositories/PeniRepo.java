package com.example.PeniCalc.repositories;

import com.example.PeniCalc.entities.Peni;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeniRepo extends JpaRepository<Peni, Integer> {
}
