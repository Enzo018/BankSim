/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.flux.model;

import com.bootcamp.banksim.mod.currency.model.CurrencyEntity;
import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author Enzo
 */
@Entity
public class TransferEntity {

  @Id
  private String id;

  //vars
  private BigDecimal amount;
  private String state;
  private String senderSwift;
  private String senderIban;
  private String senderDocumentType;
  private String senderDocumentNum;
  private String senderTransactionId;
  private String senderIso;
  private String receiverSwift;
  private String receiverIban;
  private String receiverDocumentType;
  private String receiverDocumentNum;
  private String receiverTransactionId;
  private String receiverIso;
  
  //validator
  private boolean passed;

  //audit
  private Date modified;
  private String modifier;
  private Date inserted;
  private String inserter;

  //referentials
  @ManyToOne
  private CurrencyEntity currency;

  //Getters and Setters
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getSenderSwift() {
    return senderSwift;
  }

  public void setSenderSwift(String senderSwift) {
    this.senderSwift = senderSwift;
  }

  public String getSenderIban() {
    return senderIban;
  }

  public void setSenderIban(String senderIban) {
    this.senderIban = senderIban;
  }

  public String getSenderDocumentType() {
    return senderDocumentType;
  }

  public void setSenderDocumentType(String senderDocumentType) {
    this.senderDocumentType = senderDocumentType;
  }

  public String getSenderDocumentNum() {
    return senderDocumentNum;
  }

  public void setSenderDocumentNum(String senderDocumentNum) {
    this.senderDocumentNum = senderDocumentNum;
  }

  public String getReceiverSwift() {
    return receiverSwift;
  }

  public void setReceiverSwift(String receiverBankSwift) {
    this.receiverSwift = receiverBankSwift;
  }

  public String getReceiverIban() {
    return receiverIban;
  }

  public void setReceiverIban(String receiverAccIban) {
    this.receiverIban = receiverAccIban;
  }

  public String getReceiverDocumentType() {
    return receiverDocumentType;
  }

  public void setReceiverDocumentType(String receiverDocumentType) {
    this.receiverDocumentType = receiverDocumentType;
  }

  public String getReceiverDocumentNum() {
    return receiverDocumentNum;
  }

  public void setReceiverDocumentNum(String receiverDocumentNum) {
    this.receiverDocumentNum = receiverDocumentNum;
  }

  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  public String getModifier() {
    return modifier;
  }

  public void setModifier(String modifier) {
    this.modifier = modifier;
  }

  public Date getInserted() {
    return inserted;
  }

  public void setInserted(Date inserted) {
    this.inserted = inserted;
  }

  public String getInserter() {
    return inserter;
  }

  public void setInserter(String inserter) {
    this.inserter = inserter;
  }

  public CurrencyEntity getCurrency() {
    return currency;
  }

  public void setCurrency(CurrencyEntity currency) {
    this.currency = currency;
  }

  public String getSenderTransactionId() {
    return senderTransactionId;
  }

  public void setSenderTransactionId(String senderTransactionId) {
    this.senderTransactionId = senderTransactionId;
  }

  public String getReceiverTransactionId() {
    return receiverTransactionId;
  }

  public void setReceiverTransactionId(String receiverTransactionId) {
    this.receiverTransactionId = receiverTransactionId;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getSenderIso() {
    return senderIso;
  }

  public void setSenderIso(String senderIso) {
    this.senderIso = senderIso;
  }

  public String getReceiverIso() {
    return receiverIso;
  }

  public void setReceiverIso(String receiverIso) {
    this.receiverIso = receiverIso;
  }

  public boolean isPassed() {
    return passed;
  }

  public void setPassed(boolean passed) {
    this.passed = passed;
  }

  
}
