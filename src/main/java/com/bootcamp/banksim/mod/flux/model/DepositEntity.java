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
public class DepositEntity {

  @Id
  private String id;

  //vars
  private String transactionId;
  private String state;
  private BigDecimal amount;
  private String swift;
  private String iban;
  private String documentType;
  private String documentNum;
  private String iso;

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

  public String getSwift() {
    return swift;
  }

  public void setSwift(String receiverBankSwift) {
    this.swift = receiverBankSwift;
  }

  public String getIban() {
    return iban;
  }

  public void setIban(String receiverAccIban) {
    this.iban = receiverAccIban;
  }

  public String getDocumentType() {
    return documentType;
  }

  public void setDocumentType(String receiverDocumentType) {
    this.documentType = receiverDocumentType;
  }

  public String getDocumentNum() {
    return documentNum;
  }

  public void setDocumentNum(String receiverDocumentNum) {
    this.documentNum = receiverDocumentNum;
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

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getIso() {
    return iso;
  }

  public void setIso(String iso) {
    this.iso = iso;
  }

}
