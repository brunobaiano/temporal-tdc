package com.github.brunobaiano.generatePerson;

import java.util.Locale;
import net.datafaker.providers.entertainment.EntertainmentFaker;
import net.datafaker.providers.entertainment.EntertainmentProviders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/person")
public class GeneratePersonController {

  public record Person(String name, String planet) {}

  Logger logger = LoggerFactory.getLogger(GeneratePersonController.class);

  @GetMapping
  public Person generatePerson() {
    EntertainmentProviders entertainmentProviders = new EntertainmentFaker(new Locale("pt-BR"));
    Person person = new Person(entertainmentProviders.starWars().character(),
        entertainmentProviders.starWars().planets());
    logger.info("generatePerson retornou {}", person);
    return person;

  }
}
