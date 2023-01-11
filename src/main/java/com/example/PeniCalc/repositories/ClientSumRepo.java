package com.example.PeniCalc;

import com.example.PeniCalc.entities.ClientSumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientSumRepo extends JpaRepository<ClientSumEntity, Integer> {

    
}
