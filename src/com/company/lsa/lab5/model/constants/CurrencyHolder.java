package com.company.lsa.lab5.model.constants;

import com.company.lsa.lab5.model.money.Currency;

import java.util.HashMap;
import java.util.Map;

public class CurrencyHolder {
    private static final Map<String, Currency> currencies = new HashMap<>();

    static {
        currencies.put("USD", new Currency("USD", 1));
        currencies.put("RUR", new Currency("RUR", 65.5f));
    }

    private CurrencyHolder() {
    }

    public static Currency getCurrencyByName(String name) {
        return currencies.get(name);
    }
}