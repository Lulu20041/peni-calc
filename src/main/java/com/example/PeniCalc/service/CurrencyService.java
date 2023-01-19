package com.example.PeniCalc.service;

import com.example.PeniCalc.entities.Currency;
import com.example.PeniCalc.repositories.CurrencyRepo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepo currencyRepo;

    public void loadCurrencies() throws Exception {
        URL url = new URL("https://www.nbrb.by/api/exrates/rates?periodicity=0");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode != 200) {
            throw new RuntimeException("HttpResponceCode: " + responseCode);
        }
        else {
            StringBuilder jsonString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                jsonString.append(scanner.nextLine());
            }

            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(String.valueOf(jsonString));

            for (int i = 0; i < json.size(); i++) {
                JSONObject object = (JSONObject) json.get(i);
                System.out.println(object);

                Currency currency = new Currency();

                currency.setId(((Long) object.get("Cur_ID")).intValue());
                currency.setName((String) object.get("Cur_Name"));
                currency.setOfficialRate((Double) object.get("Cur_OfficialRate"));

                currencyRepo.save(currency);
            }
        }
    }
}
