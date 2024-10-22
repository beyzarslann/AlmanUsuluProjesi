package com.example.AlmanUsuluProjesi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TotalAndAverageAmount {

   private double toplamHarcama ;
   private double ortalamaHarcama;
   private double toplamHesapDoviz;

}
