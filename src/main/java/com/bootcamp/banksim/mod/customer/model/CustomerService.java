/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.customer.model;

import com.bootcamp.banksim.mod.account.model.AccountEntity;
import com.bootcamp.banksim.mod.account.model.AccountRepository;
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
public class CustomerService {

  @Autowired
  private CustomerRepository repository;

  public CustomerEntity create(CustomerEntity arg) {

    var entity = new CustomerEntity();
    var date = new Date();

    //Id
    entity.setId(UUID.randomUUID().toString());

    //vars
    entity.setBirthdate(arg.getBirthdate());
    entity.setDocumentNum(arg.getDocumentNum());
    entity.setDocumentType(arg.getDocumentType());
    entity.setEmail(arg.getEmail());
    entity.setFirstName(arg.getFirstName());
    entity.setLastName(arg.getLastName());
    entity.setPhone(arg.getPhone());

    //validator
    entity.setEnabled(true);

    //audit
    entity.setInserted(date);
    entity.setInserter("Ni idea");
    entity.setModified(date);
    entity.setModifier("Ni idea");

    return repository.save(entity);

  }

  public List<CustomerEntity> list() {

    return repository.findAll();
  }

  public CustomerEntity update(String id, CustomerEntity arg) {

    CustomerEntity entity = repository.findById(id).get();
    var date = new Date();

    //vars
    entity.setBirthdate(arg.getBirthdate());
    entity.setDocumentNum(arg.getDocumentNum());
    entity.setDocumentType(arg.getDocumentType());
    entity.setEmail(arg.getEmail());
    entity.setFirstName(arg.getFirstName());
    entity.setLastName(arg.getLastName());
    entity.setPhone(arg.getPhone());

    //audit
    entity.setModified(date);
    entity.setModifier("Ni idea");

    return repository.save(entity);

  }

  public CustomerEntity find(String id) {

    CustomerEntity entity = repository.findById(id).get();

    return repository.save(entity);

  }

  public CustomerEntity enable(CustomerEntity arg) {

    //vars
    var date = new Date();

    //validator
    arg.setEnabled(true);

    //audits
    arg.setModified(date);
    arg.setModifier("Ni idea");

    return repository.save(arg);
  }

  public CustomerEntity disable(CustomerEntity arg) {

    //vars
    var date = new Date();

    //validator
    arg.setEnabled(false);

    //audits
    arg.setModified(date);
    arg.setModifier("Ni idea");

    return repository.save(arg);
  }
  
  public CustomerEntity findByDocument(String documentNum, String documentType){
    
    return repository.findByDocumentNumAndDocumentType(documentNum, documentType);
  }
}
