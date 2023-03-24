package com.github.brunobaiano.createAccount.controller;

import com.github.brunobaiano.createAccount.domain.Account;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/account")
public class AccountController {


  final AccountProcessor accountProcessor;

  public AccountController(AccountProcessor accountProcessor) {
    this.accountProcessor = accountProcessor;
  }

  @PostMapping
  public Account createAccount(@RequestHeader(value = "Idempotency-Key") String idempotencyKey) {
    return accountProcessor.createAccount(idempotencyKey);
  }


}
