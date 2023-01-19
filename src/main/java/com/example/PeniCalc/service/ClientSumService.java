package com.example.PeniCalc.service;

import com.example.PeniCalc.entities.ClientSum;
import com.example.PeniCalc.entities.Currency;
import com.example.PeniCalc.repositories.ClientSumRepo;
import com.example.PeniCalc.repositories.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class ClientSumService {
    @Autowired
    private ClientSumRepo clientSumRepo;
    @Autowired
    private CurrencyRepo currencyRepo;
    public void addSum(ClientSum clientSum) {
        clientSumRepo.save(clientSum);
    }
    public List<ClientSum> getSums() {
        return clientSumRepo.findAll();
    }
    public List<Currency> getCurrencies() {
        return currencyRepo.findAll();
    }
    public void deleteSum(int id) {
        clientSumRepo.deleteById(id);
    }
}
