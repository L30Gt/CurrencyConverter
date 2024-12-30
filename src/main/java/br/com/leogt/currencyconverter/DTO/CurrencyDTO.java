package br.com.leogt.currencyconverter.DTO;

import com.google.gson.annotations.SerializedName;

public record CurrencyDTO (@SerializedName("error-type") String errorType, String base_code, String target_code, double conversion_rate, double conversion_result) {
}
