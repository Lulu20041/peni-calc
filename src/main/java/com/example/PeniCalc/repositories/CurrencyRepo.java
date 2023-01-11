package com.example.PeniCalc.repositories;

import com.example.PeniCalc.entities.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepo extends JpaRepository<CurrencyEntity, Integer> {

}
