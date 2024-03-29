/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.currency.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Enzo
 */
@Service
public class CurrencyService {

  @Autowired
  private CurrencyRepository repository;

  public CurrencyEntity create(CurrencyEntity arg) {

    var entity = new CurrencyEntity();
    var date = new Date();

    //Id
    entity.setId(UUID.randomUUID().toString());

    //vars
    entity.setTitle(arg.getTitle());
    entity.setIsoNum(arg.getIsoNum());
    entity.setIso(arg.getIso());
    entity.setDecimals(arg.getDecimals());
    entity.setSymbol(arg.getSymbol());

    //validator
    entity.setEnabled(true);

    //audit
    entity.setInserted(date);
    entity.setInserter("Ni idea");
    entity.setModified(date);
    entity.setModifier("Ni idea");

    return repository.save(entity);

  }

  public List<CurrencyEntity> list() {

    return repository.findAll();
  }

  public CurrencyEntity update(CurrencyEntity currency, CurrencyEntity arg) {

    var date = new Date();

    //vars
    currency.setTitle(arg.getTitle());
    currency.setIsoNum(arg.getIsoNum());
    currency.setIso(arg.getIso());
    currency.setDecimals(arg.getDecimals());
    currency.setSymbol(arg.getSymbol());

    //audit
    currency.setModified(date);
    currency.setModifier("Ni idea");

    return repository.save(currency);

  }

  public CurrencyEntity find(String id) {

    CurrencyEntity entity = repository.findById(id).get();

    return repository.save(entity);

  }

  public void enable(CurrencyEntity arg) {

    var date = new Date();

    //validator
    arg.setEnabled(true);

    //audit
    arg.setModified(date);
    arg.setModifier("Ni idea");

    repository.save(arg);
  }

  public void disable(CurrencyEntity arg) {

    var date = new Date();

    //validator
    arg.setEnabled(false);

    //audit
    arg.setModified(date);
    arg.setModifier("Ni idea");

    repository.save(arg);
  }

  public CurrencyEntity findByIso(String iso) {

    return repository.findByIso(iso);
  }
}
