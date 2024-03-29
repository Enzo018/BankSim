/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bootcamp.banksim.mod.account.model;

import com.bootcamp.banksim.mod.customer.model.CustomerEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Enzo
 */

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
  
  public AccountEntity findByIban(String iban);
  
  public AccountEntity findByTitle(String title);
  
  public List<AccountEntity> findAllByCustomer(CustomerEntity customer);
  
  public List<AccountEntity> countAllByCustomer(CustomerEntity customer);
  
  public List<AccountEntity> findAllByEnabled(boolean enabled);

  public List<AccountEntity> countAllByEnabled(boolean enabled);
    
}
