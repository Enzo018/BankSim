/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.customer.model;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 *
 * @author Enzo
 */
@Entity
public class CustomerEntity {

  @Id
  private String id;

  //vars
  private String documentType;
  private String documentNum;
  private String firstName;
  private String lastName;
  private Date birthdate;
  private String email;
  private String phone;

  //validators
  private boolean enabled;

  //audit
  private Date modified;
  private String modifier;
  private Date inserted;
  private String inserter;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDocumentType() {
    return documentType;
  }

  public void setDocumentType(String documentType) {
    this.documentType = documentType;
  }

  public String getDocumentNum() {
    return documentNum;
  }

  public void setDocumentNum(String documentNum) {
    this.documentNum = documentNum;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
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

}
