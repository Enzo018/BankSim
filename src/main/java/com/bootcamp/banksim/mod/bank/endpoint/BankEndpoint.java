/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.bank.endpoint;

import com.bootcamp.banksim.mod.bank.model.BankEntity;
import com.bootcamp.banksim.mod.bank.model.BankService;
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
@RequestMapping("/banks")
public class BankEndpoint {

  @Autowired
  private BankService service;

  @PostMapping
  public ResponseEntity register(@RequestBody BankEntity request) {

    try {
      //Checking validity
      if (request.getTitle() == null || request.getTitle().equals("")) {
        throw new NullPointerException("El título insertado para crear el banco no es válido");
      }
      if (request.getSwift() == null || request.getSwift().equals("")) {
        throw new NullPointerException("El swift insertado para crear el banco no es válido");
      }
      if (request.getPort() == null || request.getPort().equals("")) {
        throw new NullPointerException("El puerto insertado para crear el banco no es válido");
      }
      if (service.haveBank() && request.isMyBank()) {
        throw new NullPointerException("Ya existe un banco asignado como mi banco");
      }

      //Customer Creation
      BankEntity bank = service.create(request);
      if (bank == null) {
        throw new NullPointerException("Fallo en la creación del banco");
      }

      return ResponseEntity.ok(bank);

    } catch (Throwable t) {
      return ResponseEntity.status(500).body(t.getMessage());
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
        throw new NullPointerException("El ID insertado para encontrar el banco no es válido");
      }

      //Checking existence
      BankEntity bank = service.find(id);
      if (bank == null) {
        throw new NullPointerException("No existe un banco con el ID especificado");
      }

      return ResponseEntity.ok(bank);

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody BankEntity request, @PathVariable("id") String id) {

    try {
      //Checking validity
      if (id == null || id.equals("")) {
        throw new NullPointerException("El ID insertado para encontrar el banco no es válido");
      }
      if (request.getTitle() == null || request.getTitle().equals("")) {
        throw new NullPointerException("El título insertado para crear el banco no es válido");
      }
      if (request.getSwift() == null || request.getSwift().equals("")) {
        throw new NullPointerException("El swift insertado para crear el banco no es válido");
      }
      if (request.getPort() == null || request.getPort().equals("")) {
        throw new NullPointerException("El puerto insertado para crear el banco no es válido");
      }

      //Checking existence
      BankEntity bank = service.find(id);
      if (bank == null) {
        throw new NullPointerException("No existe un banco con el ID especificado");
      }

      //Update
      bank = service.update(bank, request);
      if (bank == null) {
        throw new NullPointerException("Fallo en la actualización del banco");
      }

      return ResponseEntity.ok(bank);

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }

  @PutMapping("/{id}/enable")
  public ResponseEntity enable(@PathVariable("id") String id) {

    try {
      //Checking validity
      if (id == null || id.equals("")) {
        throw new NullPointerException("El ID insertado para encontrar el banco no es válido");
      }

      //Checking existence
      BankEntity bank = service.find(id);
      if (bank == null) {
        throw new NullPointerException("No existe un banco con el ID especificado");
      }

      //Enable
      service.enable(bank);

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
        throw new NullPointerException("El ID insertado para encontrar el banco no es válido");
      }

      //Checking existence
      BankEntity bank = service.find(id);
      if (bank == null) {
        throw new NullPointerException("No existe un banco con el ID especificado");
      }

      //Disable
      service.disable(bank);

      return ResponseEntity.status(200).build();

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }

}
