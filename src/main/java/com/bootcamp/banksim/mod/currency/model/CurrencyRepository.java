/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.currency.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Enzo
 */
@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, String> {

  public CurrencyEntity findByTitle(String title);

  public CurrencyEntity findByIso(String iso);

  public CurrencyEntity findByIsoNum(int isoNum);

  public CurrencyEntity findAllBySymbol(char symbol);

  public List<CurrencyEntity> findAllByEnabled(boolean enabled);

  public List<CurrencyEntity> countAllByEnabled(boolean enabled);

}
