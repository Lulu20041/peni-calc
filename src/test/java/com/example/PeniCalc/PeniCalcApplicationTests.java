package com.example.PeniCalc;

import com.example.PeniCalc.entities.Currency;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

@SpringBootTest
class PeniCalcApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	void loadCurrencies() throws Exception {
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


			}
		}
	}

}
