package com.github.brunobaiano.createAccount.domain;

import java.util.UUID;

public record Account(UUID id, Person person, CreditCard creditCard) {}
