package com.github.brunobaiano.createAccount.activities;

import com.github.brunobaiano.createAccount.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PersonActivityImpl implements PersonActivity {

  Logger logger = LoggerFactory.getLogger(PersonActivityImpl.class);

  public Person createPerson() {
    WebClient webClient = WebClient.builder().baseUrl("http://localhost:18081").build();
    Person person = webClient.get().uri("/v1/person")
        .retrieve()
        .bodyToMono(Person.class).block();
    return person;
  }

}
