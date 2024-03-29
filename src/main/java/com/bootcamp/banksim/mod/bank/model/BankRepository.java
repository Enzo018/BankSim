/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.bank.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Enzo
 */
@Repository
public interface BankRepository extends JpaRepository<BankEntity, String> {

  public BankEntity findByTitle(String title);

  public BankEntity findBySwift(String swift);

  public BankEntity findByMyBank(boolean myBank);

  public List<BankEntity> findAllByEnabled(boolean enabled);

  public List<BankEntity> countAllByEnabled(boolean enabled);

}
