package com.github.brunobaiano.createAccount.activities;

import com.github.brunobaiano.createAccount.domain.Person;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface PersonActivity {


  Person createPerson();
}
