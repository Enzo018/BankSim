/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.account.model;

import com.bootcamp.banksim.mod.customer.model.CustomerEntity;
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
public class AccountService {

  @Autowired
  private AccountRepository repository;

  public AccountEntity create(AccountEntity arg) {

    var entity = new AccountEntity();
    var date = new Date();

    //Id
    entity.setId(UUID.randomUUID().toString());

    //vars
    entity.setIban(arg.getIban());
    entity.setTitle(arg.getTitle());

    //validator
    entity.setEnabled(true);

    //audit
    entity.setInserted(date);
    entity.setInserter("Ni idea");
    entity.setModified(date);
    entity.setModifier("Ni idea");

    //referentials
    entity.setCustomer(arg.getCustomer());
    entity.setCurrency(arg.getCurrency());

    return repository.save(entity);

  }

  public List<AccountEntity> list() {

    return repository.findAll();
  }

  public AccountEntity update(AccountEntity entity, AccountEntity arg) {

    var date = new Date();

    //vars
    entity.setIban(arg.getIban());
    entity.setTitle(arg.getTitle());

    //audit
    entity.setModified(date);
    entity.setModifier("Ni idea");

    //referentials
    entity.setCustomer(arg.getCustomer());
    entity.setCurrency(arg.getCurrency());

    return repository.save(entity);

  }

  public AccountEntity find(String id) {

    AccountEntity entity = repository.findById(id).get();

    return repository.save(entity);

  }

  public AccountEntity enable(AccountEntity arg) {

    var date = new Date();

    //validator
    arg.setEnabled(true);

    //audit
    arg.setModified(date);
    arg.setModifier("Ni idea");

    return repository.save(arg);
  }

  public AccountEntity disable(AccountEntity arg) {

    var date = new Date();

    //validator
    arg.setEnabled(false);

    //audit
    arg.setModified(date);
    arg.setModifier("Ni idea");

    return repository.save(arg);
  }

  public List<AccountEntity> listAccountsByCustomer(CustomerEntity customer) {

    return repository.findAllByCustomer(customer);
  }

  public AccountEntity findByIban(String iban) {

    return repository.findByIban(iban);
  }
}
