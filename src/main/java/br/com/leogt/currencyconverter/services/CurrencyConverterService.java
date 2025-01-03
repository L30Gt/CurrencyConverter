package br.com.leogt.currencyconverter.services;

import br.com.leogt.currencyconverter.DTO.CurrencyDTO;
import br.com.leogt.currencyconverter.UI.UIMenu;
import br.com.leogt.currencyconverter.exceptions.InvalidCurrencyCodeException;
import br.com.leogt.currencyconverter.models.Currency;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrencyConverterService {
    private static final List<String> historyList = new ArrayList<>();
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");
    static Scanner scanner = new Scanner(System.in);


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

            if ("unsupported-code".equalsIgnoreCase(currencyDTO.errorType()) ||
                    "malformed-request".equalsIgnoreCase(currencyDTO.errorType())) {
                throw new InvalidCurrencyCodeException("Invalid currency code(s): " + originCurrency + " or " + targetCurrency);
            }

            return new Currency(currencyDTO);

        } catch (IOException e) {
            System.err.println("Error while connecting to the API");
        } catch (InterruptedException e) {
            System.err.println("The request was interrupted");
        }
        return null;
    }

    public static double getAmountFromUser() {

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

        LocalDateTime dateTime = LocalDateTime.now();
        String formattedTime = dateTime.format(dateTimeFormatter);

        if (convertedResult < 0.01)
            formatedResult = String.format("The amount of %.2f [%s] is equivalent to %.6f [%s] at %s", amount, currency.getBaseCode(), convertedResult, currency.getTargetCode(), formattedTime);
        else
            formatedResult = String.format("The amount of %.2f [%s] is equivalent to %.2f [%s] at %s", amount, currency.getBaseCode(), convertedResult, currency.getTargetCode(), formattedTime);

        System.out.println();
        System.out.println(formatedResult);
        historyList.add(formatedResult);
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
        try {
            System.out.print("Insert the origin currency code: ");
            String originCurrency = scanner.nextLine().toUpperCase();
            System.out.print("Insert the target currency code: ");
            String targetCurrency = scanner.nextLine().toUpperCase();

            double amount = getAmountFromUser();
            convertCurrency(originCurrency, targetCurrency, amount);
        } catch (InvalidCurrencyCodeException e) {
            System.err.println(e.getMessage());
            System.out.println();
            UIMenu.pressEnter(scanner);
        }

    }

    public static void showHistory() {
        System.out.println("""
                ********************************************************************************
                
                ------------------------------ CONVERSION HISTORY ------------------------------
                """);

        if(historyList.isEmpty()) {
            System.out.println("No conversion history found.");
        }

        for (String history : historyList) {
            System.out.printf("%s\n", history);
        }
        System.out.println("\n--------------------------------------------------------------------------------\n");
    }
}
