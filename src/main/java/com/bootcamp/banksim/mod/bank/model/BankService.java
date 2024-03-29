/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.bank.model;

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
public class BankService {

  @Autowired
  private BankRepository repository;

  public BankEntity create(BankEntity arg) {

    var entity = new BankEntity();
    var date = new Date();

    //Id
    entity.setId(UUID.randomUUID().toString());

    //vars
    entity.setTitle(arg.getTitle());
    entity.setSwift(arg.getSwift());
    entity.setPort(arg.getPort());

    //validator
    entity.setMyBank(arg.isMyBank());
    entity.setEnabled(true);

    //audit
    entity.setInserted(date);
    entity.setInserter("Ni idea");
    entity.setModified(date);
    entity.setModifier("Ni idea");

    return repository.save(entity);
  }

  public List<BankEntity> list() {

    return repository.findAll();
  }

  public BankEntity update(BankEntity entity, BankEntity arg) {

    var date = new Date();

    //vars
    entity.setTitle(arg.getTitle());
    entity.setSwift(arg.getSwift());
    entity.setPort(arg.getPort());

    //audit
    entity.setModified(date);
    entity.setModifier("Ni idea");

    return repository.save(entity);

  }

  public BankEntity find(String id) {

    BankEntity entity = repository.findById(id).get();

    return repository.save(entity);

  }

  public BankEntity enable(BankEntity arg) {

    var date = new Date();

    arg.setEnabled(true);

    //audit
    arg.setModified(date);
    arg.setModifier("Ni idea");

    return repository.save(arg);
  }

  public BankEntity disable(BankEntity arg) {

    var date = new Date();

    arg.setEnabled(false);

    //audit
    arg.setModified(date);
    arg.setModifier("Ni idea");

    return repository.save(arg);
  }

  public BankEntity findBySwift(String swift) {

    return repository.findBySwift(swift);
  }

  public boolean isMyBank(BankEntity bank) {

    String bankSwift = bank.getSwift();
    String mySwift = repository.findByMyBank(true).getSwift();

    return bankSwift.equals(mySwift);
  }
  
  public boolean haveBank(){
    
    return repository.findByMyBank(true) != null;
  }
  
  public BankEntity findMyBank(){
    
    return repository.findByMyBank(true);
  }
}
