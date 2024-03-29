/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.banksim.mod.flux.action;

import com.bootcamp.banksim.app.action.BanksimAction;
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
import com.bootcamp.banksim.mod.flux.model.TransferEntity;
import com.bootcamp.banksim.mod.flux.model.TransferService;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Enzo
 */
public class TransferAction {
  /*
  private final BanksimAction action = new BanksimAction();
  @Autowired
  private TransferService service;
  @Autowired
  private BankService bankService;
  @Autowired
  private AccountService accountService;
  @Autowired
  private AccountBalanceService balanceService;
  @Autowired
  private CurrencyService currencyService;
  @Autowired
  private CustomerService customerService;
  @Autowired
  private AccountMovementService movementService;
  @Autowired
  private RestTemplate restTemplate;

  public void Validate(TransferEntity request) {

    if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) == -1) {
      throw new NullPointerException("El monto de la transacción insertado para crear la transferencia no es válido");
    }
    if (request.getSenderSwift() == null || request.getSenderSwift().equals("")) {
      throw new NullPointerException("El swift del emisor insertado para crear la transferencia no es válido");
    }
    if (request.getSenderIban() == null || request.getSenderIban().equals("")) {
      throw new NullPointerException("El IBAN del emisor insertado para crear la transferencia no es válido");
    }
    if (!request.getSenderDocumentType().equals("Pasaporte") && !request.getSenderDocumentType().equals("Cédula")) {
      throw new NullPointerException("El tipo de documento del emisor insertado para crear la transferencia no es válido");
    }
    if (request.getSenderDocumentNum() == null || request.getSenderDocumentNum().equals("")) {
      throw new NullPointerException("El número de documento del emisor insertado para crear la transferencia no es válido");
    }
    if (request.getSenderIso() == null || request.getSenderIso().equals("")) {
      throw new NullPointerException("El código ISO del emisor insertado para crear la transferencia no es válido");
    }
    if (request.getReceiverSwift() == null || request.getReceiverSwift().equals("")) {
      throw new NullPointerException("El swift del receptor insertado para crear la transferencia no es válido");
    }
    if (request.getReceiverIban() == null || request.getReceiverIban().equals("")) {
      throw new NullPointerException("El IBAN del receptor insertado para crear la transferencia no es válido");
    }
    if (!request.getReceiverDocumentType().equals("Pasaporte") && !request.getReceiverDocumentType().equals("Cédula")) {
      throw new NullPointerException("El tipo de documento del receptor insertado para crear la transferencia no es válido");
    }
    if (request.getReceiverDocumentNum() == null || request.getReceiverDocumentNum().equals("")) {
      throw new NullPointerException("El número de documento del receptor insertado para crear la transferencia no es válido");
    }
    if (request.getReceiverIso() == null || request.getReceiverIso().equals("")) {
      throw new NullPointerException("El código ISO del receptor insertado para crear la transferencia no es válido");
    }
  }

  public void Ensure(TransferEntity request) {

    BankEntity senderBank = bankService.findBySwift(request.getSenderSwift());
    BankEntity receiverBank = bankService.findBySwift(request.getReceiverSwift());
    CurrencyEntity senderCurrency = currencyService.findByIso(request.getSenderIso());
    CurrencyEntity receiverCurrency = currencyService.findByIso(request.getReceiverIso());
    AccountEntity senderAccount = accountService.findByIban(request.getSenderIban());
    AccountEntity receiverAccount = accountService.findByIban(request.getReceiverIban());
    AccountBalanceEntity senderBalance = balanceService.findByAccount(senderAccount);
    AccountBalanceEntity receiverBalance = balanceService.findByAccount(receiverAccount);
    CustomerEntity senderCustomer = customerService.findByDocument(request.getSenderDocumentNum(), request.getSenderDocumentType());
    CustomerEntity receiverCustomer = customerService.findByDocument(request.getReceiverDocumentNum(), request.getReceiverDocumentType());

    action.EnsureBank(senderBank);
    action.EnsureBank(receiverBank);

    if (bankService.isMyBank(senderBank)) {

      action.EnsureCurrency(senderCurrency);
      action.EnsureAccount(senderAccount);
      action.EnsureBalance(senderBalance);
      action.EnsureCustomer(senderCustomer);
    }

    if (bankService.isMyBank(receiverBank)) {

      action.EnsureCurrency(receiverCurrency);
      action.EnsureAccount(receiverAccount);
      action.EnsureBalance(receiverBalance);
      action.EnsureCustomer(receiverCustomer);
    }
  }

  public void Consistence(TransferEntity request) {

    BankEntity senderBank = bankService.findBySwift(request.getSenderSwift());
    BankEntity receiverBank = bankService.findBySwift(request.getReceiverSwift());
    CurrencyEntity senderCurrency = currencyService.findByIso(request.getSenderIso());
    CurrencyEntity receiverCurrency = currencyService.findByIso(request.getReceiverIso());
    AccountEntity senderAccount = accountService.findByIban(request.getSenderIban());
    AccountEntity receiverAccount = accountService.findByIban(request.getReceiverIban());
    AccountBalanceEntity senderBalance = balanceService.findByAccount(senderAccount);
    CustomerEntity senderCustomer = customerService.findByDocument(request.getSenderDocumentNum(), request.getSenderDocumentType());
    CustomerEntity receiverCustomer = customerService.findByDocument(request.getReceiverDocumentNum(), request.getReceiverDocumentType());

    if (bankService.isMyBank(senderBank)) {

      String senderAccountIso = senderAccount.getCurrency().getIso();
      if (!senderAccountIso.equals(senderCurrency.getIso())) {
        throw new NullPointerException("La moneda especificada del emisor no concuerda con la moneda de la cuenta especificada del emisor");
      }
      if (!senderAccount.isEnabled()) {
        throw new NullPointerException("La cuenta especificada del emisor no esta habilitada");
      }
      if (!senderAccount.getCustomer().equals(senderCustomer)) {
        throw new NullPointerException("El cliente especificado del emisor no concuerda con el cliente de la cuenta especificada del emisor");
      }
      if (senderBalance.getAmount().compareTo(request.getAmount()) == -1) {
        throw new NullPointerException("El balance del emisor es insuficiente para realizzar la transferencia");
      }
    }

    if (bankService.isMyBank(receiverBank)) {

      String receiverAccountIso = receiverAccount.getCurrency().getIso();
      if (!receiverAccountIso.equals(receiverCurrency.getIso())) {
        throw new NullPointerException("La moneda especificada del receptor no concuerda con la moneda de la cuenta especificada del receptor");
      }
      if (!receiverAccount.isEnabled()) {
        throw new NullPointerException("La cuenta especificada del receptor no esta habilitada");
      }
      if (!receiverAccount.getCustomer().equals(receiverCustomer)) {
        throw new NullPointerException("El cliente especificado del receptor no concuerda con el cliente de la cuenta especificada del receptor");
      }
    }
  }

  public TransferEntity Send(TransferEntity request) {

    CurrencyEntity senderCurrency = currencyService.findByIso(request.getSenderIso());
    AccountEntity senderAccount = accountService.findByIban(request.getSenderIban());
    AccountBalanceEntity senderBalance = balanceService.findByAccount(senderAccount);
    BankEntity receiverBank = bankService.findBySwift(request.getReceiverSwift());

    //Transfer Creation
    request.setCurrency(senderCurrency);
    request.setState("Created");
    request.setSenderTransactionId(UUID.randomUUID().toString());
    TransferEntity transfer = service.create(request);
    if (transfer == null) {
      throw new NullPointerException("Fallo en la creación de la transferencia emisora");
    }

    //Movement Creation
    var movement = new AccountMovementEntity();

    movement.setAccount(senderAccount);
    movement.setAmount(transfer.getAmount());
    movement.setBalance(senderBalance);
    movement.setCurrency(senderCurrency);
    movement.setDebit(null);
    movement.setDeposit(null);
    movement.setTransactionId(transfer.getSenderTransactionId());
    movement.setTransactionType("Transfer");
    movement.setTransfer(transfer);

    movement = movementService.create(movement);
    if (movement == null) {
      throw new NullPointerException("Fallo en la creación del movimiento emisor");
    }

    //Affect Balance
    senderBalance = balanceService.subtract(senderBalance, transfer.getAmount());
    if (senderBalance == null) {
      throw new NullPointerException("Fallo en la actualización del balance emisor");
    }

    //Send Transfer to other Bank
    var headers = new HttpHeaders();
    var entity = new HttpEntity<TransferEntity>(transfer, headers);

    TransferEntity otherBankTransfer = restTemplate.exchange("http://localhost:" + receiverBank.getPort() + "/transfers", HttpMethod.POST, entity, TransferEntity.class).getBody();

    if (otherBankTransfer == null || otherBankTransfer.getState().equals("Reverted")) {
      this.Revert(transfer);
      throw new NullPointerException("Fallo en la transferencia en el otro banco");
    }

    transfer.setReceiverTransactionId(otherBankTransfer.getReceiverTransactionId());
    service.updateState(transfer, "Completed");

    return transfer;
  }

  public TransferEntity Receive(TransferEntity request) {

    CurrencyEntity receiverCurrency = currencyService.findByIso(request.getReceiverIso());
    AccountEntity receiverAccount = accountService.findByIban(request.getReceiverIban());
    AccountBalanceEntity receiverBalance = balanceService.findByAccount(receiverAccount);

    //Transfer Creation
    request.setState("Created");
    request.setReceiverTransactionId(UUID.randomUUID().toString());
    TransferEntity transfer = service.create(request);
    if (transfer == null) {
      throw new NullPointerException("Fallo en la creación de la transferencia receptora");
    }
    //Movement Creation
    var movement = new AccountMovementEntity();

    movement.setAccount(receiverAccount);
    movement.setAmount(transfer.getAmount());
    movement.setBalance(receiverBalance);
    movement.setCurrency(receiverCurrency);
    movement.setDebit(null);
    movement.setDeposit(null);
    movement.setTransactionId(transfer.getReceiverTransactionId());
    movement.setTransactionType("Transfer");
    movement.setTransfer(transfer);

    movement = movementService.create(movement);
    if (movement == null) {
      throw new NullPointerException("Fallo en la creación del movimiento receptor");
    }

    //Affect Balance
    receiverBalance = balanceService.add(receiverBalance, transfer.getAmount());
    if (receiverBalance == null) {
      throw new NullPointerException("Fallo en la actualización del balance receptor");
    }

    service.updateState(transfer, "Completed");

    return transfer;
  }

  public TransferEntity FullTransfer(TransferEntity request) {

    CurrencyEntity senderCurrency = currencyService.findByIso(request.getSenderIso());
    AccountEntity senderAccount = accountService.findByIban(request.getSenderIban());
    AccountBalanceEntity senderBalance = balanceService.findByAccount(senderAccount);
    CurrencyEntity receiverCurrency = currencyService.findByIso(request.getReceiverIso());
    AccountEntity receiverAccount = accountService.findByIban(request.getReceiverIban());
    AccountBalanceEntity receiverBalance = balanceService.findByAccount(receiverAccount);

    //Transfer Creation
    request.setCurrency(senderCurrency);
    request.setState("Created");
    request.setSenderTransactionId(UUID.randomUUID().toString());
    request.setReceiverTransactionId(request.getSenderTransactionId());
    TransferEntity transfer = service.create(request);
    if (transfer == null) {
      throw new NullPointerException("Fallo en la creación de la transferencia emisora");
    }

    //Sender Movement Creation
    var senderMovement = new AccountMovementEntity();

    senderMovement.setAccount(senderAccount);
    senderMovement.setAmount(transfer.getAmount());
    senderMovement.setBalance(senderBalance);
    senderMovement.setCurrency(senderCurrency);
    senderMovement.setDebit(null);
    senderMovement.setDeposit(null);
    senderMovement.setTransactionId(transfer.getSenderTransactionId());
    senderMovement.setTransactionType("Transfer");
    senderMovement.setTransfer(transfer);

    senderMovement = movementService.create(senderMovement);
    if (senderMovement == null) {
      throw new NullPointerException("Fallo en la creación del movimiento emisor");
    }

    //Receiver Movement Creation
    var receiverMovement = new AccountMovementEntity();

    receiverMovement.setAccount(receiverAccount);
    receiverMovement.setAmount(transfer.getAmount());
    receiverMovement.setBalance(receiverBalance);
    receiverMovement.setCurrency(receiverCurrency);
    receiverMovement.setDebit(null);
    receiverMovement.setDeposit(null);
    receiverMovement.setTransactionId(transfer.getReceiverTransactionId());
    receiverMovement.setTransactionType("Transfer");
    receiverMovement.setTransfer(transfer);

    receiverMovement = movementService.create(receiverMovement);
    if (receiverMovement == null) {
      throw new NullPointerException("Fallo en la creación del movimiento receptor");
    }

    //Affect Sender Balance
    senderBalance = balanceService.subtract(senderBalance, transfer.getAmount());
    if (senderBalance == null) {
      throw new NullPointerException("Fallo en la actualización del balance emisor");
    }

    //Affect Receiver Balance
    receiverBalance = balanceService.add(receiverBalance, transfer.getAmount());
    if (receiverBalance == null) {
      throw new NullPointerException("Fallo en la actualización del balance receptor");
    }

    service.updateState(transfer, "Completed");

    return transfer;
  }

  public void Revert(TransferEntity transfer) {

    //Revert Movement
    var movement = new AccountMovementEntity();
    CurrencyEntity senderCurrency = currencyService.findByIso(transfer.getSenderIso());
    AccountEntity senderAccount = accountService.findByIban(transfer.getSenderIban());
    AccountBalanceEntity senderBalance = balanceService.findByAccount(senderAccount);

    movement.setAccount(senderAccount);
    movement.setAmount(transfer.getAmount());
    movement.setBalance(senderBalance);
    movement.setCurrency(senderCurrency);
    movement.setDebit(null);
    movement.setDeposit(null);
    movement.setTransactionId(transfer.getSenderTransactionId());
    movement.setTransactionType("Transfer Refund");
    movement.setTransfer(transfer);

    movement = movementService.create(movement);
    if (movement == null) {
      throw new NullPointerException("Fallo en la creación del movimiento de reembolso");
    }

    //Affect Balance
    senderBalance = balanceService.add(senderBalance, transfer.getAmount());
    if (senderBalance == null) {
      throw new NullPointerException("Fallo en la actualización del balance emisor");
    }

    //Revert State
    service.updateState(transfer, "Reverted");
  }
*/
}
