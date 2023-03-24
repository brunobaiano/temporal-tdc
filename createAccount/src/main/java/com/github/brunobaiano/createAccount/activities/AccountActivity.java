package com.github.brunobaiano.createAccount.activities;

import com.github.brunobaiano.createAccount.domain.Account;
import com.github.brunobaiano.createAccount.domain.CreditCard;
import com.github.brunobaiano.createAccount.domain.Person;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface AccountActivity {

  Account createAccount(Person person, CreditCard creditCard);
}
