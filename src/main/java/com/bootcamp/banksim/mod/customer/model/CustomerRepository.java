/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.customer.model;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Enzo
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

  public CustomerEntity findByFirstNameAndLastName(String firstName, String lastName);

  public CustomerEntity findByDocumentNum(String documentNum);
  
  public CustomerEntity findByDocumentNumAndDocumentType(String documentNum, String documentType);

  public CustomerEntity findByEmail(String email);

  public List<CustomerEntity> findAllByLastName(String lastName);

  public List<CustomerEntity> findAllByPhone(String phone);

  public List<CustomerEntity> findAllByBirthdate(Date birthdate);

  public List<CustomerEntity> countAllByBirthdate(Date birthdate);

  public List<CustomerEntity> findAllByEnabled(boolean enabled);

  public List<CustomerEntity> countAllByEnabled(boolean enabled);

}
