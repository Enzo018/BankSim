/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.account.endpoint;

import com.bootcamp.banksim.mod.account.model.AccountBalanceEntity;
import com.bootcamp.banksim.mod.account.model.AccountBalanceService;
import com.bootcamp.banksim.mod.account.model.AccountEntity;
import com.bootcamp.banksim.mod.account.model.AccountMovementEntity;
import com.bootcamp.banksim.mod.account.model.AccountMovementService;
import com.bootcamp.banksim.mod.account.model.AccountService;
import com.bootcamp.banksim.mod.currency.model.CurrencyEntity;
import com.bootcamp.banksim.mod.currency.model.CurrencyService;
import com.bootcamp.banksim.mod.customer.model.CustomerEntity;
import com.bootcamp.banksim.mod.customer.model.CustomerService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Enzo
 */
@RestController
@RequestMapping("/accounts")
public class AccountEndpoint {

  @Autowired
  private AccountService service;
  @Autowired
  private CurrencyService currencyService;
  @Autowired
  private CustomerService customerService;
  @Autowired
  private AccountBalanceService balanceService;
  @Autowired
  private AccountMovementService movementService;

  @PostMapping
  public ResponseEntity register(@RequestBody AccountEntity request) {

    try {
      //Checking validity
      if (request.getIban() == null || request.getIban().equals("")) {
        throw new NullPointerException("El IBAN insertado para crear la cuenta no es válido");
      }
      if (request.getTitle() == null || request.getTitle().equals("")) {
        throw new NullPointerException("El título insertado para crear la cuenta no es válido");
      }
      if (request.getCustomer() == null || request.getCustomer().getId() == null) {
        throw new NullPointerException("El cliente referenciado para crear la cuenta no es válido");
      }
      if (request.getCurrency() == null || request.getCurrency().getId() == null) {
        throw new NullPointerException("La moneda referenciada para crear la cuenta no es válida");
      }

      //Checking existence
      CustomerEntity customer = customerService.find(request.getCustomer().getId());
      if (customer == null) {
        throw new NullPointerException("El cliente referenciado para crear la cuenta no existe");
      }
      request.setCustomer(customer);
      
      CurrencyEntity currency = currencyService.find(request.getCurrency().getId());
      if (currency == null) {
        throw new NullPointerException("La moneda referenciada para crear la cuenta no existe");
      }
      request.setCurrency(currency);

      //Account creation
      AccountEntity account = service.create(request);
      if (account == null) {
        throw new NullPointerException("Fallo en la creación de la cuenta");
      }
      
      //Balance creation
      var balance = new AccountBalanceEntity();
      balance.setAccount(account);
      balance.setCurrency(account.getCurrency());
      balance.setAmount(BigDecimal.ZERO);
      balance = balanceService.create(balance);
      if (balance == null) {
        throw new NullPointerException("Fallo en la creación del balance de la cuenta");
      }
      
      return ResponseEntity.ok(account);

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }

  @GetMapping
  public ResponseEntity list() {

    try {
      return ResponseEntity.ok(service.list());

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity find(@PathVariable("id") String id) {

    try {
      //Checking validity
      if (id == null || id.equals("")) {
        throw new NullPointerException("El ID insertado para encontrar la cuenta no es válido");
      }
      
      //Checking existence
      var entity = service.find(id);
      if (entity == null) {
        throw new NullPointerException("No existe una cuenta con el ID especificado");
      }
      
      return ResponseEntity.ok(entity);

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody AccountEntity request, @PathVariable("id") String id) {

    try {
      //Checking validity
      if (id == null || id.equals("")) {
        throw new NullPointerException("El ID insertado para encontrar la cuenta no es válido");
      }
      if (request.getIban() == null || request.getIban().equals("")) {
        throw new NullPointerException("El IBAN insertado para actualizar la cuenta no es válido");
      }
      if (request.getTitle() == null || request.getTitle().equals("")) {
        throw new NullPointerException("El título insertado para actualizar la cuenta no es válido");
      }
      if (request.getCustomer() == null || request.getCustomer().getId() == null) {
        throw new NullPointerException("El cliente referenciado para actualizar la cuenta no es válido");
      }
      if (request.getCurrency() == null || request.getCurrency().getId() == null) {
        throw new NullPointerException("La moneda referenciada para actualizar la cuenta no es válida");
      }

      //Checking existence
      AccountEntity entity = service.find(id);
      if (entity == null) {
        throw new NullPointerException("No existe una cuenta con el ID especificado");
      }
      
      CustomerEntity customer = customerService.find(request.getCustomer().getId());
      if (customer == null) {
        throw new NullPointerException("El cliente referenciado para actualizar la cuenta no existe");
      }
      request.setCustomer(customer);
      
      CurrencyEntity currency = currencyService.find(request.getCurrency().getId());
      if (currency == null) {
        throw new NullPointerException("La moneda referenciada para actualizar la cuenta no existe");
      }
      request.setCurrency(currency);
      
      //Update
      entity = service.update(entity, request);
      if (entity == null) {
        throw new NullPointerException("Fallo en la actualización de la cuenta");
      }
      
      return ResponseEntity.ok(entity);

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }

  @PutMapping("/{id}/enable")
  public ResponseEntity enable(@PathVariable("id") String id) {

    try {
      //Checking validity
      if (id == null || id.equals("")) {
        throw new NullPointerException("El ID insertado para encontrar la cuenta no es válido");
      }
      
      //Checking existence
      AccountEntity entity = service.find(id);
      if (entity == null) {
        throw new NullPointerException("No existe una cuenta con el ID especificado");
      }
      
      //Enable
      service.enable(entity);
      
      return ResponseEntity.status(200).build();

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }

  @PutMapping("/{id}/disable")
  public ResponseEntity disable(@PathVariable("id") String id) {

    try {
      //Checking validity
      if (id == null || id.equals("")) {
        throw new NullPointerException("El ID insertado para encontrar la cuenta no es válido");
      }
      
      //Checking existence
      AccountEntity entity = service.find(id);
      if (entity == null) {
        throw new NullPointerException("No existe una cuenta con el ID especificado");
      }
      
      //Disable
      service.disable(entity);
      
      return ResponseEntity.status(200).build();

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }
  
  @GetMapping("/{id}/balance")
  public ResponseEntity findBalance(@PathVariable("id") String id) {

    try {
      //Checking validity
      if (id == null || id.equals("")) {
        throw new NullPointerException("El ID insertado para encontrar la cuenta no es válido");
      }
      
      //Checking existence
      AccountEntity entity = service.find(id);
      if (entity == null) {
        throw new NullPointerException("No existe una cuenta con el ID especificado");
      }
      
      AccountBalanceEntity balance = balanceService.findByAccount(entity);
      if (balance == null) {
        throw new NullPointerException("La cuenta especificada no tiene ningún balance asociado a esta");
      }
      
      return ResponseEntity.ok(balance);

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }
  
  @GetMapping("/{id}/movements")
  public ResponseEntity listMovements(@PathVariable("id") String id){
    
    try {
      //Checking validity
      if (id == null || id.equals("")) {
        throw new NullPointerException("El ID insertado para encontrar la cuenta no es válido");
      }
      
      //Checking existence
      AccountEntity account = service.find(id);
      if (account == null) {
        throw new NullPointerException("No existe una cuenta con el ID especificado");
      }
      
      List<AccountMovementEntity> movements = movementService.findAllByAccount(account);
      if (movements == null) {
        throw new NullPointerException("La cuenta especificada no tiene movimientos asociados a esta");
      }
      
      return ResponseEntity.ok(movements);

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }
}
