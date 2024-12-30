package br.com.leogt.currencyconverter.services;

import br.com.leogt.currencyconverter.DTO.CurrencyDTO;
import br.com.leogt.currencyconverter.models.Currency;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class CurrencyConverterService {

    public static Currency currencyApi(String originCurrency, String targetCurrency, double amount) {
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
            return new Currency(currencyDTO);

        } catch (Exception e) {
            throw new RuntimeException("Error in currency conversion");
        }
    }

    public static double getAmountFromUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Insert the amount you want to convert [Leave blank or 0 (zero) for 1.00]: ");
        String amountString = scanner.nextLine().replace(",", ".");
        return Double.parseDouble(amountString.isEmpty() || amountString.equals(0) ? "1" : amountString);
    }

    public static void convertCurrency(String originCurrency, String targetCurrency, double amount) {
        Currency currency = currencyApi(originCurrency, targetCurrency, amount);
        System.out.printf("The amount of %.2f [%s] is equivalent to %.2f [%s].", amount, currency.getBaseCode(), currency.getConversionResult(), currency.getTargetCode());
    }

    public static void USDtoARS() {
        double amount = getAmountFromUser();
        convertCurrency("USD", "ARS", amount);
    }

    public static void ARStoUSD() {
        double amount = getAmountFromUser();
        convertCurrency("ARS", "USD", amount);
    }

    public static void USDtoBRL() {
        double amount = getAmountFromUser();
        convertCurrency("USD", "BRL", amount);
    }

    public static void BRLtoUSD() {
        double amount = getAmountFromUser();
        convertCurrency("BRL", "USD", amount);
    }

    public static void USDtoCOP() {
        double amount = getAmountFromUser();
        convertCurrency("USD", "COP", amount);
    }

    public static void COPtoUSD() {
        double amount = getAmountFromUser();
        convertCurrency("COP", "USD", amount);
    }

    public static void otherCurrencies() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert the origin currency code: ");
        String originCurrency = scanner.nextLine().toUpperCase();
        System.out.print("Insert the target currency code: ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        double amount = getAmountFromUser();
        convertCurrency(originCurrency, targetCurrency, amount);
    }
}
