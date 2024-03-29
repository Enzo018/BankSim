/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.account.model;

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
public class AccountMovementService {

  @Autowired
  private AccountMovementRepository repository;

  public AccountMovementEntity create(AccountMovementEntity arg) {

    var entity = new AccountMovementEntity();
    var date = new Date();

    //Id
    entity.setId(UUID.randomUUID().toString());

    //vars
    entity.setAmount(arg.getAmount());
    entity.setTransactionType(arg.getTransactionType());
    entity.setTransactionId(arg.getTransactionId());

    //audit
    entity.setInserted(date);
    entity.setInserter("Ni idea");
    entity.setModified(date);
    entity.setModifier("Ni idea");

    //referentials
    entity.setCurrency(arg.getCurrency());
    entity.setAccount(arg.getAccount());
    entity.setBalance(arg.getBalance());
    entity.setDebit(arg.getDebit());
    entity.setDeposit(arg.getDeposit());
    entity.setTransfer(arg.getTransfer());

    return repository.save(entity);
  }
  
  public List<AccountMovementEntity> findAllByAccount(AccountEntity account){
    
    return repository.findAllByAccount(account);
  }
}
