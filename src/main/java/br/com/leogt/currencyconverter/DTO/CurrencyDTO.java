package br.com.leogt.currencyconverter.DTO;

public record CurrencyDTO (String base_code, String target_code, double conversion_rate, double conversion_result) {
}
