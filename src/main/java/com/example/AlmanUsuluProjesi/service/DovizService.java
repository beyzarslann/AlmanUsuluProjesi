package com.example.AlmanUsuluProjesi.service;

import dovizAPI.Currency;
import dovizAPI.CurrencyFactory;
import dovizAPI.Moneys;
import org.springframework.stereotype.Service;

@Service
public class DovizService {

    public float getDovizBuying(String dovizType) {
        CurrencyFactory factory = new CurrencyFactory(Moneys.valueOf(dovizType));
        Currency cur = factory.getCurrency();
        float buying = cur.getBuyingPrice();

        return buying;
    }

    public float getDovizSelling(String dovizType) {
        CurrencyFactory factory = new CurrencyFactory(Moneys.valueOf(dovizType));
        Currency cur = factory.getCurrency();
        float selling = cur.getSellingPrice();

        return selling;
    }

}
