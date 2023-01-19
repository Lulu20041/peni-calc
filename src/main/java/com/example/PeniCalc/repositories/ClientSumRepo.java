package com.example.PeniCalc.repositories;

import com.example.PeniCalc.entities.ClientSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientSumRepo extends JpaRepository<ClientSum, Integer> {

}
