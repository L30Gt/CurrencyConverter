package br.com.leogt.currencyconverter.models;

import br.com.leogt.currencyconverter.DTO.CurrencyDTO;
import com.google.gson.annotations.SerializedName;

public class Currency {
    @SerializedName("base_code")
    private String baseCode;
    @SerializedName("target_code")
    private String targetCode;
    @SerializedName("conversion_rate")
    private double conversionRate;
    @SerializedName("conversion_result")
    private double conversionResult;

    public Currency() {
    }

    public Currency(CurrencyDTO currencyDTO) {
        this.baseCode = currencyDTO.base_code();
        this.targetCode = currencyDTO.target_code();
        this.conversionRate = currencyDTO.conversion_rate();
        this.conversionResult = currencyDTO.conversion_result();
    }

    @Override
    public String toString() {
        return "Currency{" +
                "baseCode='" + baseCode + '\'' +
                ", targetCode='" + targetCode + '\'' +
                ", conversionRate=" + conversionRate +
                ", conversionResult=" + conversionResult +
                '}';
    }
}
