package br.com.leogt.currencyconverter.main;
import br.com.leogt.currencyconverter.services.CurrencyConverterService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //UIConvertCurrency.displayMenu();

        Scanner scanner = new Scanner(System.in);
        String base, target;

        CurrencyConverterService currencyConverterService = new CurrencyConverterService();

        System.out.print("Insert the base currency: ");
        base = scanner.nextLine();
        System.out.print("Insert the target currency: ");
        target = scanner.nextLine();
        System.out.print("insert the value: ");
        double value = scanner.nextDouble();

        currencyConverterService.currencyApi(base, target, value);

    }
}
