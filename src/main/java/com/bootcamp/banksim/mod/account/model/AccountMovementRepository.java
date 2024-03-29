/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bootcamp.banksim.mod.account.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Enzo
 */
@Repository
public interface AccountMovementRepository extends JpaRepository<AccountMovementEntity, String> {
  
  public List<AccountMovementEntity> findAllByAccount(AccountEntity account);
}
