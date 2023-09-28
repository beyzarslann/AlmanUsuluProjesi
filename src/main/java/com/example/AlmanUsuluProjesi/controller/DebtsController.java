package com.example.AlmanUsuluProjesi.controller;

import com.example.AlmanUsuluProjesi.dto.response.InterpersonaDebtResponse;
import com.example.AlmanUsuluProjesi.dto.response.TotalAndAverageAmount;
import com.example.AlmanUsuluProjesi.model.Debts;
import com.example.AlmanUsuluProjesi.service.DebtsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/debts")
@CrossOrigin(origins = "http://localhost:4200")
public class DebtsController {

    private final DebtsService debtsService;

    public DebtsController(DebtsService debtsService) {
        this.debtsService = debtsService;
    }

    @GetMapping()
    public List<Debts> getAllDebts() {
        return debtsService.getAllDebts();
    }

    @PostMapping("/save")
    public Debts save(@RequestBody Debts yeniKullanici, String harcamaId) {
        return debtsService.save(yeniKullanici, harcamaId);
    }

    @PostMapping("/toplamveOrtalamaHarcama")
    public TotalAndAverageAmount toplamveOrtalamaHarcama(@RequestBody List<Debts> harcamalar) {
        return debtsService.calculateTotalAndAverage(harcamalar);
    }

    @PostMapping("/harcamaHesap")
    public List<InterpersonaDebtResponse> harcamaHesap(@RequestBody List<Debts> harcamalar) {
        List<InterpersonaDebtResponse> borcs = debtsService.calculateDebts(harcamalar);
        return borcs;
    }
}