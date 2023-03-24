package com.github.brunobaiano.generateCreditCard;

import net.datafaker.Faker;
import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.CreditCardType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/credit-card")
public class GenerateCreditCardController {

  public record CreditCard(String flag, String number) {}

  Logger logger = LoggerFactory.getLogger(GenerateCreditCardController.class);
  @GetMapping
  public CreditCard generateCreditCard() {

    Faker faker = new Faker();
    CreditCardType flag = CreditCardType.values()[((BaseProviders) faker).random()
        .nextInt(CreditCardType.values().length)];
    CreditCard creditCard = new CreditCard(flag.name(), faker.finance().creditCard(flag));
    logger.info("generateCreditCard retornou {}", creditCard);
    return creditCard;
  }

}
