/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.currency.endpoint;

import com.bootcamp.banksim.mod.currency.model.CurrencyEntity;
import com.bootcamp.banksim.mod.currency.model.CurrencyService;
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
@RequestMapping("/currencies")
public class CurrencyEndpoint {

  @Autowired
  private CurrencyService service;

  @PostMapping
  public ResponseEntity register(@RequestBody CurrencyEntity request) {

    try {
      //Checking validity
      if (request.getTitle() == null || request.getTitle().equals("")) {
        throw new NullPointerException("El título insertado para crear la moneda no es válido");
      }
      if (request.getIso() == null || request.getIso().equals("")) {
        throw new NullPointerException("El ISO code en letras insertado para crear la moneda no es válido");
      }
      if (request.getIsoNum() <= 0 || request.getIsoNum() >= 1000) {
        throw new NullPointerException("El ISO code insertado para crear la moneda no es válido");
      }
      if (request.getDecimals() <= 0 || request.getDecimals() >= 10) {
        throw new NullPointerException("El número de decimales insertado para crear la moneda no es válido");
      }
      if (request.getSymbol() == null || request.getSymbol().equals("")) {
        throw new NullPointerException("El símbolo insertado para crear la moneda no es válido");
      }

      //Currency Creation
      CurrencyEntity currency = service.create(request);
      if (currency == null) {
        throw new NullPointerException("Fallo en la creación de la moneda");
      }

      return ResponseEntity.ok(currency);

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
        throw new NullPointerException("El ID insertado para encontrar la moneda no es válido");
      }

      //Checking existence
      CurrencyEntity currency = service.find(id);
      if (currency == null) {
        throw new NullPointerException("No existe una moneda con el ID especificado");
      }

      return ResponseEntity.ok(currency);

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody CurrencyEntity request, @PathVariable("id") String id) {

    try {
      //Checking validity
      if (id == null || id.equals("")) {
        throw new NullPointerException("El ID insertado para actualizar la moneda no es válido");
      }
      if (request.getTitle() == null || request.getTitle().equals("")) {
        throw new NullPointerException("El título insertado para actualizar la moneda no es válido");
      }
      if (request.getIso() == null || request.getIso().equals("")) {
        throw new NullPointerException("El ISO code en letras insertado para actualizar la moneda no es válido");
      }
      if (request.getIsoNum() <= 0 || request.getIsoNum() >= 1000) {
        throw new NullPointerException("El ISO code insertado para actualizar la moneda no es válido");
      }
      if (request.getDecimals() <= 0 || request.getDecimals() >= 10) {
        throw new NullPointerException("El número de decimales insertado para actualizar la moneda no es válido");
      }
      if (request.getSymbol() == null || request.getSymbol().equals("")) {
        throw new NullPointerException("El símbolo insertado para actualizar la moneda no es válido");
      }

      //Checking existence
      CurrencyEntity currency = service.find(id);
      if (currency == null) {
        throw new NullPointerException("No existe una moneda con el ID especificado");
      }

      //Update
      currency = service.update(currency, request);
      if (currency == null) {
        throw new NullPointerException("Fallo en la actualización de la moneda");
      }

      return ResponseEntity.ok(currency);

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }

  @PutMapping("/{id}/enable")
  public ResponseEntity enable(@PathVariable("id") String id) {

    try {
      //Checking validity
      if (id == null || id.equals("")) {
        throw new NullPointerException("El ID insertado para encontrar la moneda no es válido");
      }

      //Checking existence
      CurrencyEntity currency = service.find(id);
      if (currency == null) {
        throw new NullPointerException("No existe una moneda con el ID especificado");
      }

      //Enable
      service.enable(currency);

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
        throw new NullPointerException("El ID insertado para encontrar la moneda no es válido");
      }

      //Checking existence
      CurrencyEntity currency = service.find(id);
      if (currency == null) {
        throw new NullPointerException("No existe una moneda con el ID especificado");
      }

      //Disable
      service.disable(currency);

      return ResponseEntity.status(200).build();

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }
}
