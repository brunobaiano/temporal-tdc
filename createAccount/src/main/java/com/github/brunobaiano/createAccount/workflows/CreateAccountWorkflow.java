package com.github.brunobaiano.createAccount.workflows;

import com.github.brunobaiano.createAccount.domain.Account;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface CreateAccountWorkflow {

  @WorkflowMethod
  Account createAccount();

}
