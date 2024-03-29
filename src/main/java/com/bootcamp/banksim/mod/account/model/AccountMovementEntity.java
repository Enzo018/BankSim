/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.account.model;

import com.bootcamp.banksim.mod.currency.model.CurrencyEntity;
import com.bootcamp.banksim.mod.flux.model.DebitEntity;
import com.bootcamp.banksim.mod.flux.model.DepositEntity;
import com.bootcamp.banksim.mod.flux.model.TransferEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Enzo
 */
@Entity
public class AccountMovementEntity {

  @Id
  private String id;

  //vars
  private BigDecimal amount;
  private String transactionType;
  private String transactionId;

  //audit
  private Date modified;
  private String modifier;
  private Date inserted;
  private String inserter;

  //referentials
  @ManyToOne
  private AccountEntity account;
  @ManyToOne
  private CurrencyEntity currency;
  @ManyToOne
  private DebitEntity debit;
  @ManyToOne
  private DepositEntity deposit;
  @ManyToOne
  private TransferEntity transfer;
  @ManyToOne
  private AccountBalanceEntity balance;

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

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
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

  public AccountEntity getAccount() {
    return account;
  }

  public void setAccount(AccountEntity account) {
    this.account = account;
  }

  public CurrencyEntity getCurrency() {
    return currency;
  }

  public void setCurrency(CurrencyEntity currency) {
    this.currency = currency;
  }

  public DebitEntity getDebit() {
    return debit;
  }

  public void setDebit(DebitEntity debit) {
    this.debit = debit;
  }

  public DepositEntity getDeposit() {
    return deposit;
  }

  public void setDeposit(DepositEntity deposit) {
    this.deposit = deposit;
  }

  public TransferEntity getTransfer() {
    return transfer;
  }

  public void setTransfer(TransferEntity transfer) {
    this.transfer = transfer;
  }

  public AccountBalanceEntity getBalance() {
    return balance;
  }

  public void setBalance(AccountBalanceEntity balance) {
    this.balance = balance;
  }
}
