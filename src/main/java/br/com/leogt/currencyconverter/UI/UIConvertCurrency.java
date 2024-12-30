package br.com.leogt.currencyconverter.UI;

import java.util.Scanner;

public class UIConvertCurrency {
    public static void displayMenu() {
        int option;
        Scanner scanner = new Scanner(System.in);

        do {
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
                    System.out.println("Dollar -> Argentine Peso");
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Argentine Peso -> Dollar");
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Dollar -> Brazilian Real");
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Brazilian Real -> Dollar");
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Dollar -> Colombian Peso");
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Colombian Peso -> Dollar");
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
