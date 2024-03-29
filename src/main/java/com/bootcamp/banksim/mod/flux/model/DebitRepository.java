/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.flux.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Enzo
 */
@Repository
public interface DebitRepository extends JpaRepository<DebitEntity, String> {

  public DebitEntity findAllByIban(String senderAccIban);

  public DebitEntity findAllByDocumentNum(String senderDocumentNum);
}
