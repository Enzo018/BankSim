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
public class TransferService {

  @Autowired
  private TransferRepository repository;

  public TransferEntity create(TransferEntity arg) {

    var entity = new TransferEntity();
    var date = new Date();

    //Id
    entity.setId(UUID.randomUUID().toString());

    //vars
    entity.setState("Created");
    entity.setAmount(arg.getAmount());
    entity.setSenderIban(arg.getSenderIban());
    entity.setSenderSwift(arg.getSenderSwift());
    entity.setSenderDocumentNum(arg.getSenderDocumentNum());
    entity.setSenderDocumentType(arg.getSenderDocumentType());
    entity.setSenderIso(arg.getSenderIso());
    entity.setSenderTransactionId(arg.getSenderTransactionId());
    entity.setReceiverIban(arg.getReceiverIban());
    entity.setReceiverSwift(arg.getReceiverSwift());
    entity.setReceiverDocumentNum(arg.getReceiverDocumentNum());
    entity.setReceiverDocumentType(arg.getReceiverDocumentType());
    entity.setReceiverIso(arg.getReceiverIso());
    entity.setReceiverTransactionId(arg.getReceiverTransactionId());
    entity.setPassed(arg.isPassed());

    //audit
    entity.setInserted(date);
    entity.setInserter("Ni idea");
    entity.setModified(date);
    entity.setModifier("Ni idea");

    //referentials
    entity.setCurrency(arg.getCurrency());

    return repository.save(entity);

  }

  public TransferEntity updateState(TransferEntity entity, String state) {

    var date = new Date();

    //vars
    entity.setState(state);

    //audit
    entity.setModified(date);
    entity.setModifier("Ni idea");

    return repository.save(entity);

  }

  public TransferEntity find(String id) {

    TransferEntity entity = repository.findById(id).get();

    return repository.save(entity);
  }
  
  public TransferEntity setReceiverTransactionId(TransferEntity transfer, String receiverTransactionId){
    
    transfer.setReceiverTransactionId(receiverTransactionId);
    
    return repository.save(transfer);
  }
}
