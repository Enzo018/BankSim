/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.customer.endpoint;

import com.bootcamp.banksim.mod.account.model.AccountService;
import com.bootcamp.banksim.mod.customer.model.CustomerEntity;
import com.bootcamp.banksim.mod.customer.model.CustomerService;
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
@RequestMapping("/customers")
public class CustomerEndpoint {

  @Autowired
  private CustomerService service;
  @Autowired
  private AccountService accountService;

  @PostMapping
  public ResponseEntity register(@RequestBody CustomerEntity request) {

    try {
      //Checking validity
      if (!request.getDocumentType().equals("Pasaporte") && !request.getDocumentType().equals("Cédula")) {
        throw new NullPointerException("El tipo de documento insertado para crear el cliente no es válido (Se requiere 'Pasaporte' o 'Cédula')");
      }
      if (request.getDocumentNum() == null || request.getDocumentNum().equals("")) {
        throw new NullPointerException("El número de documento insertado para crear el cliente no es válido");
      }
      if (request.getFirstName() == null || request.getFirstName().equals("")) {
        throw new NullPointerException("El primer nombre insertado para crear el cliente no es válido");
      }
      if (request.getLastName() == null || request.getLastName().equals("")) {
        throw new NullPointerException("El apellido insertado para crear el cliente no es válido");
      }
      if (request.getBirthdate() == null) {
        throw new NullPointerException("El cumpleaños insertado para crear el cliente no es válido");
      }
      if (request.getEmail() == null || request.getEmail().equals("")) {
        throw new NullPointerException("El email insertado para crear el cliente no es válido");
      }
      if (request.getPhone() == null || request.getPhone().equals("")) {
        throw new NullPointerException("El teléfono insertado para crear el cliente no es válido");
      }

      //Customer Creation
      CustomerEntity customer = service.create(request);
      if (customer == null) {
        throw new NullPointerException("Fallo en la creación del cliente");
      }

      return ResponseEntity.ok(customer);

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
        throw new NullPointerException("El ID insertado para encontrar el cliente no es válido");
      }

      //Checking existence
      var entity = service.find(id);
      if (entity == null) {
        throw new NullPointerException("No existe un cliente con el ID especificado");
      }

      return ResponseEntity.ok(entity);

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody CustomerEntity request, @PathVariable("id") String id) {

    try {
      //Checking validity
      if (id == null || id.equals("")) {
        throw new NullPointerException("El ID insertado para actualizar el cliente no es válido");
      }
      if (!request.getDocumentType().equals("Pasaporte") && !request.getDocumentType().equals("Cédula")) {
        throw new NullPointerException("El tipo de documento insertado para actualizar el cliente no es válido (Se requiere 'Pasaporte' o 'Cédula')");
      }
      if (request.getDocumentNum() == null || request.getDocumentNum().equals("")) {
        throw new NullPointerException("El número de documento insertado para actualizar el cliente no es válido");
      }
      if (request.getFirstName() == null || request.getFirstName().equals("")) {
        throw new NullPointerException("El primer nombre insertado para actualizar el cliente no es válido");
      }
      if (request.getLastName() == null || request.getLastName().equals("")) {
        throw new NullPointerException("El apellido insertado para actualizar el cliente no es válido");
      }
      if (request.getBirthdate() == null) {
        throw new NullPointerException("El cumpleaños insertado para actualizar el cliente no es válido");
      }
      if (request.getEmail() == null || request.getEmail().equals("")) {
        throw new NullPointerException("El email insertado para actualizar el cliente no es válido");
      }
      if (request.getPhone() == null || request.getPhone().equals("")) {
        throw new NullPointerException("El teléfono insertado para actualizar el cliente no es válido");
      }

      //Checking existence
      var entity = service.find(id);
      if (entity == null) {
        throw new NullPointerException("No existe un cliente con el ID especificado");
      }

      //Update
      entity = service.update(id, request);
      if (entity == null) {
        throw new NullPointerException("Fallo en la actualización del cliente");
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
        throw new NullPointerException("El ID insertado para encontrar el cliente no es válido");
      }

      //Checking existence
      var entity = service.find(id);
      if (entity == null) {
        throw new NullPointerException("No existe un cliente con el ID especificado");
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
        throw new NullPointerException("El ID insertado para encontrar el cliente no es válido");
      }

      //Checking existence
      var entity = service.find(id);
      if (entity == null) {
        throw new NullPointerException("No existe un cliente con el ID especificado");
      }

      //Disable
      service.disable(entity);

      return ResponseEntity.status(200).build();

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }

  @GetMapping("/{id}/accounts")
  public ResponseEntity listAccounts(@PathVariable("id") String id) {

    try {
      //Checking validity
      if (id == null || id.equals("")) {
        throw new NullPointerException("El ID insertado del cliente no es válido");
      }

      //Checking existence
      var customer = service.find(id);
      if (customer == null) {
        throw new NullPointerException("No existe un cliente con el ID especificado");
      }

      return ResponseEntity.ok(accountService.listAccountsByCustomer(customer));

    } catch (Throwable t) {
      return ResponseEntity.status(500).build();
    }
  }
}
