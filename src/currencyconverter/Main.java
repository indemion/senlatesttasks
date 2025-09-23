package currencyconverter;

import consoleconfig.Config;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, Config.getInstance().getEncoding().toString()));
        Map<String, Currency> currencies = getCurrencies();
        try(Scanner console = new Scanner(System.in, Config.getInstance().getEncoding().toString())) {
            StringBuilder sb = new StringBuilder();
            sb.append("Доступные валюты для конвертации:\n");
            for (Currency currency : currencies.values()) {
                sb.append(String.format("- %s (%s)\n", currency.getName(), currency.getCode()));
            }
            System.out.println(sb);
            System.out.print("Введите код валюты из которой хотите перевести (например, RUB): ");
            String fromCode = null;
            Currency fromCurrency = null;
            while (fromCurrency == null) {
                fromCode = console.nextLine().toUpperCase();
                fromCurrency = currencies.get(fromCode);
                if (fromCurrency == null) {
                    System.out.print(String.format("Валюта с кодом (%s) не найдена, попробуйте ввести другой код: ", fromCode));
                }
            }
            sb.setLength(0);
            sb.append(String.format("Список валют в которые можно конвертировать (%s):\n", fromCurrency.getCode()));
            for (Map.Entry<String, Double> entry : fromCurrency.getExchangeRates().entrySet()) {
                sb.append(String.format("- (%s) по курсу %f\n", entry.getKey(), entry.getValue()));
            }
            System.out.print(sb);
            System.out.print("Введите код валюты в которую хотите перевести (например, RUB): ");
            String toCode = null;
            Double rate = null;
            while (rate == null) {
                toCode = console.nextLine().toUpperCase();
                rate = fromCurrency.getExchangeRate(toCode);
                if (rate == null) {
                    System.out.print(String.format("Валюта с кодом (%s) не найдена, попробуйте ввести другой код: ", toCode));
                }
            }
            System.out.print("Введите сумму: ");
            String input;
            Double amount = null;
            while (amount == null) {
                input = console.nextLine();
                try {
                    amount = Double.valueOf(input);
                    if (amount <= 0) {
                        System.out.print("Введённая сумма должна быть больше 0, попробуйте снова: ");
                        amount = null;
                    }
                } catch (NumberFormatException e) {
                    System.out.print(String.format("Введено не число, попробуйте снова: "));
                }
            }
            System.out.println(String.format("Из %.2f (%s), получилось %.2f (%s)", amount, fromCode, amount * rate, toCode));
        }
    }

    private static Map<String, Currency> getCurrencies() {
        Map<String, Currency> currencies = new HashMap<>();
        Currency currency = new Currency("BRL", "Бразильский реал");
        currency.addExchangeRate("CNY", 1.34);
        currency.addExchangeRate("INR", 16.58);
        currency.addExchangeRate("RUB", 15.69);
        currency.addExchangeRate("ZAR", 3.27);
        currencies.put(currency.getCode(), currency);

        currency = new Currency("CNY", "Китайский юань");
        currency.addExchangeRate("BRL", 0.7466);
        currency.addExchangeRate("INR", 12.39);
        currency.addExchangeRate("RUB", 11.67);
        currency.addExchangeRate("ZAR", 2.44);
        currencies.put(currency.getCode(), currency);

        currency = new Currency("INR", "Индийская рупия");
        currency.addExchangeRate("BRL", 0.0604);
        currency.addExchangeRate("CNY", 0.0807);
        currency.addExchangeRate("RUB", 0.9438);
        currency.addExchangeRate("ZAR", 0.1972);
        currencies.put(currency.getCode(), currency);

        currency = new Currency("RUB", "Российский рубль");
        currency.addExchangeRate("BRL", 0.063732);
        currency.addExchangeRate("CNY", 0.085718);
        currency.addExchangeRate("INR", 1.06);
        currency.addExchangeRate("ZAR", 0.2089);
        currencies.put(currency.getCode(), currency);

        currency = new Currency("ZAR", "Южноафриканский рэнд");
        currency.addExchangeRate("BRL", 0.3066);
        currency.addExchangeRate("CNY", 0.4093);
        currency.addExchangeRate("INR", 5.07);
        currency.addExchangeRate("RUB", 4.79);
        currencies.put(currency.getCode(), currency);

        return currencies;
    }
}
