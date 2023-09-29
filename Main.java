package CC;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// Initialize currencies
		ArrayList<Currency> currencies = Currency.init();

		// Assuming you have an amount in a specific currency and want to convert it to Indian Rupee (INR)
		String fromCurrencyShortName = "USD"; // Replace with the source currency short name
		String toCurrencyShortName = "INR";   // Use "INR" for Indian Rupee
		Double amountToConvert = 100.0;       // Replace with the amount you want to convert

		if (fromCurrencyShortName.equals(toCurrencyShortName)) {
			System.out.println("Source and target currencies are the same. No conversion needed.");
			return;
		}

		// Find the source currency object
		Currency sourceCurrency = null;
		for (Currency currency : currencies) {
			if (currency.getShortName().equals(fromCurrencyShortName)) {
				sourceCurrency = currency;
				break;
			}
		}

		if (sourceCurrency != null) {
			// Print the contents of the exchangeValues HashMap for the source currency
			System.out.println("Exchange Values for " + sourceCurrency.getName() + " (" + sourceCurrency.getShortName() + "):");
			for (String key : sourceCurrency.getExchangeValues().keySet()) {
				Double value = sourceCurrency.getExchangeValues().get(key);
				System.out.println(key + ": " + value);
			}

			// Find the target currency exchange value
			Double exchangeValue = sourceCurrency.getExchangeValues().get(toCurrencyShortName);

			if (exchangeValue != null) {
				// Perform the currency conversion
				Double convertedAmount = Currency.convert(amountToConvert, exchangeValue);
				System.out.println(amountToConvert + " " + fromCurrencyShortName + " is equivalent to " + convertedAmount + " " + toCurrencyShortName);
			} else {
				System.out.println("Currency conversion not available.");
			}
		} else {
			System.out.println("Source currency not found.");
		}
	}
}