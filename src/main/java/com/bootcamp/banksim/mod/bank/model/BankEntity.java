/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.bank.model;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 *
 * @author Enzo
 */
@Entity
public class BankEntity {

  @Id
  private String id;

  //vars
  private String title;
  private String swift;
  private String port;

  //validators
  private boolean myBank;
  private boolean enabled;

  //audit
  private Date modified;
  private String modifier;
  private Date inserted;
  private String inserter;

  //Getters and setters
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getSwift() {
    return swift;
  }

  public void setSwift(String swift) {
    this.swift = swift;
  }

  public boolean isMyBank() {
    return myBank;
  }

  public void setMyBank(boolean myBank) {
    this.myBank = myBank;
  }

  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }
}
