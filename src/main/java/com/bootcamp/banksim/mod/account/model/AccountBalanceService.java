/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.account.model;

import java.math.BigDecimal;
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
public class AccountBalanceService {

  @Autowired
  private AccountBalanceRepository repository;

  public AccountBalanceEntity create(AccountBalanceEntity arg) {

    var entity = new AccountBalanceEntity();
    var date = new Date();

    //Id
    entity.setId(UUID.randomUUID().toString());

    //vars
    entity.setAmount(arg.getAmount());

    //validator
    entity.setEnabled(true);

    //audit
    entity.setInserted(date);
    entity.setInserter("Ni idea");
    entity.setModified(date);
    entity.setModifier("Ni idea");

    //referentials
    entity.setAccount(arg.getAccount());
    entity.setCurrency(arg.getCurrency());

    return repository.save(entity);
  }

  public List<AccountBalanceEntity> list() {

    return repository.findAll();
  }

  public AccountBalanceEntity find(String id) {

    AccountBalanceEntity entity = repository.findById(id).get();

    return repository.save(entity);
  }

  public AccountBalanceEntity update(String id, AccountBalanceEntity arg) {

    AccountBalanceEntity entity = repository.findById(id).get();
    var date = new Date();

    //vars
    entity.setAmount(arg.getAmount());

    //audit
    entity.setModified(date);
    entity.setModifier("Ni idea");

    //referentials
    entity.setAccount(arg.getAccount());
    entity.setCurrency(arg.getCurrency());

    return repository.save(entity);
  }

  public AccountBalanceEntity enable(AccountBalanceEntity arg) {

    var date = new Date();

    //validator
    arg.setEnabled(true);

    //audit
    arg.setModified(date);
    arg.setModifier("Ni idea");

    return repository.save(arg);
  }

  public AccountBalanceEntity disable(AccountBalanceEntity arg) {

    var date = new Date();

    //validator
    arg.setEnabled(false);

    //audit
    arg.setModified(date);
    arg.setModifier("Ni idea");

    return repository.save(arg);
  }

  public AccountBalanceEntity findByAccount(AccountEntity account) {

    return repository.findByAccount(account);
  }

  public AccountBalanceEntity add(AccountBalanceEntity balance, BigDecimal amount) {

    BigDecimal newAmount = balance.getAmount().add(amount);
    balance.setAmount(newAmount);
    
    return repository.save(balance);
  }
  
  public AccountBalanceEntity subtract(AccountBalanceEntity balance, BigDecimal amount) {

    BigDecimal newAmount = balance.getAmount().subtract(amount);
    balance.setAmount(newAmount);
    
    return repository.save(balance);
  }
}
