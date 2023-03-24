package com.github.brunobaiano.createAccount.activities;

import com.github.brunobaiano.createAccount.domain.CreditCard;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CreditCardActivityImpl implements CreditCardActivity {


  public CreditCard createCreditCard() {
    WebClient webClient = WebClient.builder().baseUrl("http://localhost:18082").build();
    CreditCard creditCard = webClient.get().uri("/v1/credit-card")
        .retrieve()
        .bodyToMono(CreditCard.class).block();
    return creditCard;
  }

  ;
}
