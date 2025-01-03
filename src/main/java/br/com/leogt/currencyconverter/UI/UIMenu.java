package br.com.leogt.currencyconverter.UI;

import br.com.leogt.currencyconverter.services.CurrencyConverterService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UIMenu {
    public static void startMenu() {
        int option = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                System.out.println("*".repeat(80));
                System.out.println("""
                        ------------------------------ CURRENCY CONVERTER ------------------------------
                        
                                                       1- Convert currency
                                                       2- Show history
                                                       0- Exit the program
                        
                        --------------------------------------------------------------------------------
                        """);
                System.out.println("*".repeat(80));
                System.out.print("Enter the option's number: ");
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 0:
                        exitMessage();
                        pressEnter(scanner);
                        System.exit(0);
                        break;
                    case 1:
                        UIConvertCurrency.displayMenu();
                        break;
                    case 2:
                        CurrencyConverterService.showHistory();
                        break;
                    default:
                        System.out.println("There's no option with that number.");
                        pressEnter(scanner);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                pressEnter(scanner);
            }
        } while (option != 0);

    }

    public static void exitMessage() {
        System.out.println("""
                ********************************************************************************
                
                --------------------------------------------------------------------------------
                
                                         ORACLE NEXT EDUCATION CHALLENGE
                                                MADE BY L30Gt - 2024
                                    THANKS FOR USING THE CURRENCY CONVERTER
                
                --------------------------------------------------------------------------------
                
                ********************************************************************************
                """);
    }

    public static void pressEnter(Scanner scanner) {
        System.out.print("Press enter to continue");
        scanner.nextLine();
        System.out.println();
    }
}
