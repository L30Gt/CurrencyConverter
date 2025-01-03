package br.com.leogt.currencyconverter.UI;

import br.com.leogt.currencyconverter.services.CurrencyConverterService;

import java.util.*;

public class UIConvertCurrency {
    public static void displayMenu() {
        int option;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("*".repeat(80));
            System.out.println("""
                
                1) Dollar -> Argentine Peso
                2) Argentine Peso -> Dollar
                3) Dollar -> Brazilian Real
                4) Brazilian Real -> Dollar
                5) Dollar -> Colombian Peso
                6) Colombian Peso -> Dollar
                7) Custom Currency Conversion
                0) Back to main menu
                """);
            System.out.println("*".repeat(80));
            System.out.print("Select the option: ");

            try {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
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
                    case 7:
                        moreCurrencies();
                        System.out.println();
                        CurrencyConverterService.otherCurrencies();
                        break;
                    case 0:
                        UIMenu.startMenu();
                        break;
                    default:
                        System.out.println("Invalid option! Try again.");
                        break;
                }

                if (option != 0) {
                    UIMenu.pressEnter(scanner);
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                option = -1;
            }
        } while (option != 0);
    }

    public static void moreCurrencies() {
        System.out.println("*".repeat(80));
        System.out.println("\n\t\t\t\t\t75 currencies available for conversion\n");
        System.out.println("*".repeat(80));
        System.out.println();

        Map<String, String> currencies = new TreeMap<>();
        currencies.put("AED", "UAE Dirham");
        currencies.put("AFN", "Afghan Afghani");
        currencies.put("ALL", "Albanian Lek");
        currencies.put("AMD", "Armenian Dram");
        currencies.put("AOA", "Angolan Kwanza");
        currencies.put("ARS", "Argentine Peso");
        currencies.put("AUD", "Australian Dollar");
        currencies.put("BBD", "Barbados Dollar");
        currencies.put("BDT", "Bangladeshi Taka");
        currencies.put("BGN", "Bulgarian Lev");
        currencies.put("BHD", "Bahraini Dinar");
        currencies.put("BMD", "Bermudian Dollar");
        currencies.put("BND", "Brunei Dollar");
        currencies.put("BOB", "Bolivian Boliviano");
        currencies.put("BRL", "Brazilian Real");
        currencies.put("BSD", "Bahamian Dollar");
        currencies.put("BYN", "Belarusian Ruble");
        currencies.put("CAD", "Canadian Dollar");
        currencies.put("CDF", "Congolese Franc");
        currencies.put("CHF", "Swiss Franc");
        currencies.put("CLP", "Chilean Peso");
        currencies.put("CNY", "Chinese Renminbi");
        currencies.put("COP", "Colombian Peso");
        currencies.put("CRC", "Costa Rican Colon");
        currencies.put("CUP", "Cuban Peso");
        currencies.put("CZK", "Czech Koruna");
        currencies.put("DKK", "Danish Krone");
        currencies.put("DOP", "Dominican Peso");
        currencies.put("DZD", "Algerian Dinar");
        currencies.put("EGP", "Egyptian Pound");
        currencies.put("EUR", "Euro");
        currencies.put("GBP", "Pound Sterling");
        currencies.put("GHS", "Ghanaian Cedi");
        currencies.put("HKD", "Hong Kong Dollar");
        currencies.put("HRK", "Croatian Kuna");
        currencies.put("HTG", "Haitian Gourde");
        currencies.put("HUF", "Hungarian Forint");
        currencies.put("ILS", "Israeli New Shekel");
        currencies.put("INR", "Indian Rupee");
        currencies.put("IQD", "Iraqi Dinar");
        currencies.put("IRR", "Iranian Rial");
        currencies.put("ISK", "Icelandic Króna");
        currencies.put("JMD", "Jamaican Dollar");
        currencies.put("JPY", "Japanese Yen");
        currencies.put("KES", "Kenyan Shilling");
        currencies.put("KRW", "South Korean Won");
        currencies.put("LBP", "Lebanese Pound");
        currencies.put("MAD", "Moroccan Dirham");
        currencies.put("MXN", "Mexican Peso");
        currencies.put("NGN", "Nigerian Naira");
        currencies.put("NIO", "Nicaraguan Córdoba");
        currencies.put("NOK", "Norwegian Krone");
        currencies.put("NZD", "New Zealand Dollar");
        currencies.put("PAB", "Panamanian Balboa");
        currencies.put("PEN", "Peruvian Sol");
        currencies.put("PHP", "Philippine Peso");
        currencies.put("PLN", "Polish Złoty");
        currencies.put("PYG", "Paraguayan Guaraní");
        currencies.put("QAR", "Qatari Riyal");
        currencies.put("RON", "Romanian Leu");
        currencies.put("RSD", "Serbian Dinar");
        currencies.put("RUB", "Russian Ruble");
        currencies.put("SAR", "Saudi Riyal");
        currencies.put("SEK", "Swedish Krona");
        currencies.put("SGD", "Singapore Dollar");
        currencies.put("THB", "Thai Baht");
        currencies.put("TND", "Tunisian Dinar");
        currencies.put("TRY", "Turkish Lira");
        currencies.put("TWD", "New Taiwan Dollar");
        currencies.put("UAH", "Ukrainian Hryvnia");
        currencies.put("UGX", "Ugandan Shilling");
        currencies.put("USD", "United States Dollar");
        currencies.put("UYU", "Uruguayan Peso");
        currencies.put("VES", "Venezuelan Bolívar Soberano");
        currencies.put("VND", "Vietnamese Đồng");
        currencies.put("ZAR", "South African Rand");
        int count = 0;

        for (String code : currencies.keySet()) {
            System.out.printf("%-5s: %-40s", code, currencies.get(code));
            count++;
            if (count % 2 == 0) {
                System.out.println();
            }
        }
    }
}
