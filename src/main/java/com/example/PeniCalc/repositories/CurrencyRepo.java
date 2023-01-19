package com.example.PeniCalc.repositories;

import com.example.PeniCalc.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepo extends JpaRepository<Currency, Integer> {
    Currency findByName(String name);
    Currency findById(int id);
}
