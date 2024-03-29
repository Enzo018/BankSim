/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.flux.endpoint;

import com.bootcamp.banksim.mod.account.model.AccountBalanceEntity;
import com.bootcamp.banksim.mod.account.model.AccountBalanceService;
import com.bootcamp.banksim.mod.account.model.AccountEntity;
import com.bootcamp.banksim.mod.account.model.AccountMovementEntity;
import com.bootcamp.banksim.mod.account.model.AccountMovementService;
import com.bootcamp.banksim.mod.account.model.AccountService;
import com.bootcamp.banksim.mod.bank.model.BankEntity;
import com.bootcamp.banksim.mod.bank.model.BankService;
import com.bootcamp.banksim.mod.currency.model.CurrencyEntity;
import com.bootcamp.banksim.mod.currency.model.CurrencyService;
import com.bootcamp.banksim.mod.customer.model.CustomerEntity;
import com.bootcamp.banksim.mod.customer.model.CustomerService;
import com.bootcamp.banksim.mod.flux.model.DebitEntity;
import com.bootcamp.banksim.mod.flux.model.DebitService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Enzo
 */
@RestController
@RequestMapping("/debits")
public class DebitEndpoint {

  @Autowired
  private DebitService service;
  @Autowired
  private BankService bankService;
  @Autowired
  private AccountService accountService;
  @Autowired
  private AccountBalanceService balanceService;
  @Autowired
  private CurrencyService currencyService;
  @Autowired
  private AccountMovementService movementService;
  @Autowired
  private CustomerService customerService;

  enum RevertionStage {
    NONE,
    MOVEMENT,
    BALANCE
  }

  @PostMapping
  public ResponseEntity register(@RequestBody DebitEntity request) {

    var revert = RevertionStage.NONE;
    var debit = new DebitEntity();

    try {
      //Checking validity
      if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) == -1) {
        throw new NullPointerException("El monto de la transacción insertado para crear el débito no es válido");
      }
      if (request.getSwift() == null || request.getSwift().equals("")) {
        throw new NullPointerException("El swift insertado para crear el débito no es válido");
      }
      if (request.getIban() == null || request.getIban().equals("")) {
        throw new NullPointerException("El IBAN insertado para crear el débito no es válido");
      }
      if (!request.getDocumentType().equals("Pasaporte") && !request.getDocumentType().equals("Cédula")) {
        throw new NullPointerException("El tipo de documento insertado para crear el débito no es válido");
      }
      if (request.getDocumentNum() == null || request.getDocumentNum().equals("")) {
        throw new NullPointerException("El número de documento insertado para crear el débito no es válido");
      }
      if (request.getIso() == null || request.getIso().equals("")) {
        throw new NullPointerException("El código ISO insertado para crear el débito no es válido");
      }

      //Checking existence
      BankEntity bank = bankService.findBySwift(request.getSwift());
      if (bank == null) {
        throw new NullPointerException("El banco referenciado para crear el débito no existe");
      }

      AccountEntity account = accountService.findByIban(request.getIban());
      if (account == null) {
        throw new NullPointerException("La cuenta referenciada para crear el débito no existe");
      }

      AccountBalanceEntity balance = balanceService.findByAccount(account);
      if (balance == null) {
        throw new NullPointerException("La cuenta referenciada para crear el débito no tiene un balance asociado");
      }

      CurrencyEntity currency = currencyService.findByIso(request.getIso());
      if (currency == null) {
        throw new NullPointerException("La moneda referenciada para crear el débito no existe");
      }

      CustomerEntity customer = customerService.findByDocument(request.getDocumentNum(), request.getDocumentType());
      if (customer == null) {
        throw new NullPointerException("El cliente referenciado para crear el débito no existe");
      }

      //Checking consistency
      if (!bankService.isMyBank(bank)) {
        throw new NullPointerException("El banco especificado no es este banco");
      }

      String accountIso = account.getCurrency().getIso();
      if (!accountIso.equals(currency.getIso())) {
        throw new NullPointerException("La moneda especificada no concuerda con la moneda de la cuenta especificada");
      }

      if (!account.isEnabled()) {
        throw new NullPointerException("La cuenta especificada no esta habilitada");
      }

      if (!account.getCustomer().equals(customer)) {
        throw new NullPointerException("El cliente especificado no concuerda con el cliente de la cuenta especificada");
      }

      //Debit Creation
      request.setCurrency(currency);
      request.setState("Created");
      debit = service.create(request);
      if (debit == null) {
        throw new NullPointerException("Fallo en la creación del débito");
      }

      //Movement Creation
      var movement = new AccountMovementEntity();

      movement.setAccount(account);
      movement.setAmount(debit.getAmount());
      movement.setBalance(balance);
      movement.setCurrency(currency);
      movement.setDebit(debit);
      movement.setDeposit(null);
      movement.setTransactionId(debit.getTransactionId());
      movement.setTransactionType("Debit");
      movement.setTransfer(null);

      movement = movementService.create(movement);
      revert = RevertionStage.MOVEMENT;
      if (movement == null) {
        throw new NullPointerException("Fallo en la creación del movimiento");
      }

      //Affect Balance
      balance = balanceService.subtract(balance, debit.getAmount());
      revert = RevertionStage.BALANCE;
      if (balance == null) {
        throw new NullPointerException("Fallo en la actualización del balance");
      }

      //Set State
      service.updateState(debit, "Completed");

      return ResponseEntity.ok(debit);

    } catch (Throwable t) {

      //Revertir cambio 
      //Estado revertido
      switch (revert) {
        case NONE -> {
          return ResponseEntity.status(500).body(t);
        }
        case MOVEMENT -> {
          service.updateState(debit, "Reverted");
          return ResponseEntity.status(500).body("Error: " + t.getMessage());
        }
        case BALANCE -> {
          service.updateState(debit, "Reverted");
          return ResponseEntity.status(500).body("Error: " + t.getMessage());
        }
      }
      return ResponseEntity.status(500).body("Error: " + t.getMessage());
    }
  }
}
