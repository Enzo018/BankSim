/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.flux.model;

import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Enzo
 */
@Service
public class DebitService {

  @Autowired
  private DebitRepository repository;

  public DebitEntity create(DebitEntity arg) {

    var entity = new DebitEntity();
    var date = new Date();

    //Ids
    entity.setId(UUID.randomUUID().toString());
    entity.setTransactionId(UUID.randomUUID().toString());

    //vars
    entity.setAmount(arg.getAmount());
    entity.setState(arg.getState());
    entity.setIban(arg.getIban());
    entity.setSwift(arg.getSwift());
    entity.setDocumentNum(arg.getDocumentNum());
    entity.setDocumentType(arg.getDocumentType());
    entity.setIso(arg.getIso());

    //audit
    entity.setInserted(date);
    entity.setInserter("Ni idea");
    entity.setModified(date);
    entity.setModifier("Ni idea");

    //referentials
    entity.setCurrency(arg.getCurrency());

    return repository.save(entity);

  }

  public DebitEntity updateState(DebitEntity entity, String state) {

    var date = new Date();

    //vars
    entity.setState(state);

    //audit
    entity.setModified(date);
    entity.setModifier("Ni idea");

    return repository.save(entity);
  }

  public DebitEntity find(String id) {

    DebitEntity entity = repository.findById(id).get();

    return repository.save(entity);

  }
}
