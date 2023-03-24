package com.github.brunobaiano.createAccount.activities;

import com.github.brunobaiano.createAccount.domain.Account;
import com.github.brunobaiano.createAccount.domain.CreditCard;
import com.github.brunobaiano.createAccount.domain.Person;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class AccountActivityImpl implements AccountActivity {

  public Account createAccount(Person person, CreditCard creditCard) {
    return new Account(UUID.randomUUID(), person, creditCard);
  }
}
