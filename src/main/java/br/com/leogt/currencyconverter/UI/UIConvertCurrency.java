package br.com.leogt.currencyconverter.UI;

import br.com.leogt.currencyconverter.services.CurrencyConverterService;

import java.util.Currency;
import java.util.Scanner;

public class UIConvertCurrency {
    public static void displayMenu() {
        int option;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println();
            System.out.println("*".repeat(50));
            System.out.println("Welcome to the Currency Converter! =] ");
            System.out.println();

            System.out.println("""
                1) Dollar -> Argentine Peso
                2) Argentine Peso -> Dollar
                3) Dollar -> Brazilian Real
                4) Brazilian Real -> Dollar
                5) Dollar -> Colombian Peso
                6) Colombian Peso -> Dollar
                0) Exit
                """);
            System.out.print("Select the option: ");
            option = scanner.nextInt();

            switch (option){
                case 1:
                    CurrencyConverterService.USDtoARS();
                    System.out.println();
                    break;
                case 2:
                    CurrencyConverterService.ARStoUSD();
                    System.out.println();
                    break;
                case 3:
                    CurrencyConverterService.USDtoBRL();
                    System.out.println();
                    break;
                case 4:
                    CurrencyConverterService.BRLtoUSD();
                    System.out.println();
                    break;
                case 5:
                    CurrencyConverterService.USDtoCOP();
                    System.out.println();
                    break;
                case 6:
                    CurrencyConverterService.COPtoUSD();
                    System.out.println();
                    break;
                case 0:
                    exitMessage();
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
                    break;
            }
        } while (option != 0);

    }

    public static void exitMessage() {
        System.out.println("""
                **************************************************************************
                
                --------------------------------------------------------------------------
                
                                     ORACLE NEXT EDUCATION CHALLENGE
                                            MADE BY L30Gt - 2024
                                 THANKS FOR USING THE CURRENCY CONVERTER
                
                --------------------------------------------------------------------------
                """);
    }
}
