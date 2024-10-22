package com.example.AlmanUsuluProjesi.repository;

import com.example.AlmanUsuluProjesi.model.Debts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DebtsRepository extends JpaRepository<Debts, Long> {

    @Query("SELECT kullanici FROM Debts kullanici " +
            "WHERE kullanici.kullaniciAdi LIKE %?1% ")
    List<Debts> getDebtskullaniciadi80(String keyword);


}