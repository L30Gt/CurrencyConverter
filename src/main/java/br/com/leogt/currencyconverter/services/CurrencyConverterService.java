package br.com.leogt.currencyconverter.services;

import br.com.leogt.currencyconverter.DTO.CurrencyDTO;
import br.com.leogt.currencyconverter.exceptions.InvalidCurrencyCodeException;
import br.com.leogt.currencyconverter.models.Currency;
import com.google.gson.Gson;

import java.io.IOException;
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

            if ("unsupported-code".equalsIgnoreCase(currencyDTO.errorType())) {
                throw new InvalidCurrencyCodeException("Invalid currency code(s): " + originCurrency + " or " + targetCurrency);
            }

            return new Currency(currencyDTO);

        } catch (IOException e) {
            System.err.println("Error while connecting to the API");
        } catch (InterruptedException e) {
            System.err.println("The request was interrupted");
        } catch (InvalidCurrencyCodeException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static double getAmountFromUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Insert the amount you want to convert [Leave blank for 1.00]: ");
        String amountString = scanner.nextLine().replace(",", ".");
        try {
            return Double.parseDouble(amountString.isEmpty() ? "1" : amountString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format! Try again using only numbers.");
            return getAmountFromUser();
        }
    }

    public static void convertCurrency(String originCurrency, String targetCurrency, double amount) {
        Currency currency = currencyApi(originCurrency, targetCurrency, amount);

        if (currency == null) {
            System.err.println("Currency conversion failed due to invalid currency codes!");
            return;
        }

        double convertedResult = currency.getConversionResult();
        String formatedResult;

        if (convertedResult < 0.01)
            formatedResult = String.format("The amount of %.2f [%s] is equivalent to %.6f [%s]", amount, currency.getBaseCode(), convertedResult, currency.getTargetCode());
        else
            formatedResult = String.format("The amount of %.2f [%s] is equivalent to %.2f [%s]", amount, currency.getBaseCode(), convertedResult, currency.getTargetCode());

        System.out.println(formatedResult);
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
