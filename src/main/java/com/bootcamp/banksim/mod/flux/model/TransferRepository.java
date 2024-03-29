/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.flux.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Enzo
 */
@Repository
public interface TransferRepository extends JpaRepository<TransferEntity, String> {

  public TransferEntity findAllBySenderTransactionId(String senderTransactionId);

  public TransferEntity findAllByReceiverTransactionId(String receiverTransactionId);

  public TransferEntity findAllBySenderTransactionIdAndReceiverTransactionId(String senderTransactionId, String receiverTransactionId);

  public TransferEntity findAllBySenderIban(String senderAccIban);

  public TransferEntity findAllBySenderDocumentNum(String senderDocumentNum);

  public TransferEntity findAllByReceiverIban(String receiverAccIban);

  public TransferEntity findAllByReceiverDocumentNum(String receiverDocumentNum);

  public TransferEntity findAllBySenderIbanAndReceiverIban(String senderAccIban, String receiverAccIban);

  public TransferEntity findAllBySenderDocumentNumAndReceiverDocumentNum(String senderDocumentNum, String receiverDocumentNum);
}
