package com.github.brunobaiano.createAccount.workflows;

import com.github.brunobaiano.createAccount.domain.Account;
import com.github.brunobaiano.createAccount.domain.CreditCard;
import com.github.brunobaiano.createAccount.domain.Person;
import com.github.brunobaiano.createAccount.activities.AccountActivity;
import com.github.brunobaiano.createAccount.activities.CreditCardActivity;
import com.github.brunobaiano.createAccount.activities.PersonActivity;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class CreateAccountWorkflowImpl implements CreateAccountWorkflow{

  private static final String CREATE_ACCOUNT = "create-account";
  // RetryOptions specify how to automatically handle retries when Activities fail.
  private final RetryOptions retryoptions = RetryOptions.newBuilder()
      .setInitialInterval(Duration.ofSeconds(1))
      .setMaximumInterval(Duration.ofSeconds(100))
      .setBackoffCoefficient(2)
      .setMaximumAttempts(500)
      .build();
  private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
      // Timeout options specify when to automatically timeout Activities if the process is taking too long.
      .setStartToCloseTimeout(Duration.ofSeconds(5))
      // Optionally provide customized RetryOptions.
      // Temporal retries failures by default, this is simply an example.
      .setRetryOptions(retryoptions)
      .build();

  // ActivityStubs enable calls to methods as if the Activity object is local, but actually perform an RPC.
  private final Map<String, ActivityOptions> perActivityMethodOptions = new HashMap<String, ActivityOptions>() {{
    put(CREATE_ACCOUNT,
        ActivityOptions.newBuilder().setHeartbeatTimeout(Duration.ofSeconds(5)).build());
  }};

  private final AccountActivity accountActivity = Workflow.newActivityStub(AccountActivity.class,
      defaultActivityOptions, perActivityMethodOptions);

  private final PersonActivity personActivity = Workflow.newActivityStub(PersonActivity.class,
      defaultActivityOptions, perActivityMethodOptions);

  private final CreditCardActivity creditCardActivity = Workflow.newActivityStub(CreditCardActivity.class,
      defaultActivityOptions, perActivityMethodOptions);

  @Override
  public Account createAccount() {
    Person person = personActivity.createPerson();
    CreditCard creditCard = creditCardActivity.createCreditCard();
    return accountActivity.createAccount(person,creditCard);
  }
}
