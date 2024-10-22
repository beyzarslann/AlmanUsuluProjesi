package com.example.AlmanUsuluProjesi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InterpersonaDebtResponse {
    private String borcluIsim;
    private String alacakliIsim;
    private double borcMiktari;
    private double borcMiktariDoviz;
}
