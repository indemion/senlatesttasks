package currencyconverter;

import java.util.HashMap;
import java.util.Map;

public class Currency {
    private final String code;
    private final String name;
    private final Map<String, Double> exchangeRates = new HashMap<>();

    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void addExchangeRate(String code, Double rate) {
        exchangeRates.put(code, rate);
    }

    public Double getExchangeRate(String code) {
        return exchangeRates.get(code);
    }

    public Map<String, Double> getExchangeRates() {
        return exchangeRates;
    }
}
