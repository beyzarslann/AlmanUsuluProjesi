package com.example.AlmanUsuluProjesi.service;

import com.example.AlmanUsuluProjesi.dto.response.InterpersonaDebtResponse;
import com.example.AlmanUsuluProjesi.dto.response.TotalAndAverageAmount;
import com.example.AlmanUsuluProjesi.model.Debts;
import com.example.AlmanUsuluProjesi.repository.DebtsRepository;
import dovizAPI.Moneys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DebtsService {

    @Autowired
    private DebtsRepository debtsRepository;

    public List<Debts> getAllDebts() {
        return debtsRepository.findAll();
    }

    public Debts save(Debts yeniKullanici, String harcamaId) {
        yeniKullanici.setHarcamaId(harcamaId);
        return debtsRepository.save(yeniKullanici);
    }

    public TotalAndAverageAmount calculateTotalAndAverage(List<Debts> debtsList) {
        double toplamHarcama = 0;
        for (Debts debt : debtsList) {
            toplamHarcama += debt.getKullaniciHarcama();
        }

        double ortalamaHarcama = toplamHarcama / debtsList.size();

        DecimalFormat formatter = new DecimalFormat("0.00");
        DovizService dovizService = new DovizService();
        double dovizSatisFiyat = dovizService.getDovizSelling(String.valueOf(Moneys.US_DOLLAR));
        double toplamHesapDoviz = (toplamHarcama/dovizSatisFiyat);
        toplamHesapDoviz = Double.parseDouble(formatter.format(toplamHesapDoviz).replace(",","."));

        TotalAndAverageAmount totalAndAverageAmount = new TotalAndAverageAmount(toplamHarcama, ortalamaHarcama, toplamHesapDoviz);
        totalAndAverageAmount.setOrtalamaHarcama(ortalamaHarcama);
        totalAndAverageAmount.setToplamHarcama(toplamHarcama);
        totalAndAverageAmount.setToplamHesapDoviz(toplamHesapDoviz);


        return totalAndAverageAmount;
    }

    public List<InterpersonaDebtResponse> calculateDebts(List<Debts> debtsList) {
        List<InterpersonaDebtResponse> borclarHesabi = new ArrayList<>();
        Map<String, Double> kullaniciToplamHesap = new HashMap<>();

        double toplamHarcama = 0;
        for (Debts debt : debtsList) {
            toplamHarcama += debt.getKullaniciHarcama();
        }

        double ortalamaHarcama = toplamHarcama / debtsList.size();



        for (Debts debt : debtsList) {
            String isim = debt.getKullaniciAdi();
            double harcama = debt.getKullaniciHarcama();

            kullaniciToplamHesap.put(isim, harcama - ortalamaHarcama);
        }

        for (Debts borcluKullanici : debtsList) {
            String borcluKisi = borcluKullanici.getKullaniciAdi();

            for (Debts alacakliKullanici : debtsList) {
                if (!borcluKullanici.equals(alacakliKullanici)) {
                    String alacakliKisi = alacakliKullanici.getKullaniciAdi();

                    double borcMiktari = Math.min(kullaniciToplamHesap.get(alacakliKisi), -kullaniciToplamHesap.get(borcluKisi));
                    if (borcMiktari > 0) {
                        borcluKullanici.setKullaniciHarcama(borcluKullanici.getKullaniciHarcama() + borcMiktari);
                        alacakliKullanici.setKullaniciHarcama(alacakliKullanici.getKullaniciHarcama() - borcMiktari);

                        DecimalFormat formatter = new DecimalFormat("0.00");
                        DovizService dovizService = new DovizService();
                        double dovizSatisFiyat = dovizService.getDovizSelling(String.valueOf(Moneys.US_DOLLAR));
                        double borcMiktariDoviz = (borcMiktari/dovizSatisFiyat);
                        borcMiktariDoviz = Double.parseDouble(formatter.format(borcMiktariDoviz).replace(",","."));

                        InterpersonaDebtResponse borc = new InterpersonaDebtResponse(borcluKisi, alacakliKisi, borcMiktari, borcMiktariDoviz);
                        borclarHesabi.add(borc);
                    }
                }
            }
        }
        return borclarHesabi;
    }
}