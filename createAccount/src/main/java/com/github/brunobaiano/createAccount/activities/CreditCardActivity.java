package com.github.brunobaiano.createAccount.activities;

import com.github.brunobaiano.createAccount.domain.CreditCard;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface CreditCardActivity {

  CreditCard createCreditCard();
}
