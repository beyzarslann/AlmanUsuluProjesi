package com.example.AlmanUsuluProjesi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Data
@Entity
@Table(name  = "kullanici")

public class Debts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;

    private String kullaniciAdi;

    private double kullaniciHarcama;

    private String harcamaId;
}