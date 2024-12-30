package br.com.leogt.currencyconverter.services;

import br.com.leogt.currencyconverter.DTO.CurrencyDTO;
import br.com.leogt.currencyconverter.models.Currency;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConverterService {
    public void currencyApi(String originCurrency, String targetCurrency, double amount) {
        try {
            String address = "https://v6.exchangerate-api.com/v6/1d6e4812424daa6e3a1c9dec/pair/" + originCurrency + "/" + targetCurrency + "/" + amount;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(address))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            CurrencyDTO currencyDTO = gson.fromJson(response.body(), CurrencyDTO.class);
            Currency currency = new Currency(currencyDTO);
            System.out.println(currency);

        } catch (Exception e) {
            throw new RuntimeException("Error in currency conversion");
        }
    }
}
