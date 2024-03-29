/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.account.model;

import com.bootcamp.banksim.mod.currency.model.CurrencyEntity;
import com.bootcamp.banksim.mod.customer.model.CustomerEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;

/**
 *
 * @author Enzo
 */
@Entity
public class AccountEntity {

  @Id
  private String id;

  //vars
  private String iban;
  private String title;

  //validators
  private boolean enabled;

  //metadata management
  private Date modified;
  private String modifier;
  private Date inserted;
  private String inserter;

  //referentials
  @ManyToOne
  private CustomerEntity customer;
  @ManyToOne
  private CurrencyEntity currency;

  //fields
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
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

  public CustomerEntity getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerEntity customer) {
    this.customer = customer;
  }

  public CurrencyEntity getCurrency() {
    return currency;
  }

  public void setCurrency(CurrencyEntity currency) {
    this.currency = currency;
  }

}
