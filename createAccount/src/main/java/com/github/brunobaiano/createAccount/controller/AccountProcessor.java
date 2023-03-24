package com.github.brunobaiano.createAccount.controller;

import com.github.brunobaiano.createAccount.domain.Account;
import com.github.brunobaiano.createAccount.workflows.CreateAccountWorkflow;
import io.temporal.api.enums.v1.WorkflowIdReusePolicy;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.stereotype.Component;

@Component
public class AccountProcessor {


  public Account createAccount(String idempotencyKey) {
    // WorkflowServiceStubs is a gRPC stubs wrapper that talks to the local Docker instance of the Temporal server.
    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    WorkflowOptions options = WorkflowOptions.newBuilder()
        .setTaskQueue("CREATE_ACCOUNT_TASK_QUEUE")
        // A WorkflowId prevents this it from having duplicate instances, remove it to duplicate.
        .setWorkflowId("create-account-workflow-" + idempotencyKey)
//        .setWorkflowIdReusePolicy(WorkflowIdReusePolicy.WORKFLOW_ID_REUSE_POLICY_ALLOW_DUPLICATE_FAILED_ONLY)
        .build();
    // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
    WorkflowClient client = WorkflowClient.newInstance(service);
    // WorkflowStubs enable calls to methods as if the Workflow object is local, but actually perform an RPC.
    CreateAccountWorkflow workflow = client.newWorkflowStub(CreateAccountWorkflow.class, options);
    // Asynchronous execution. This process will exit after making this call.
    return workflow.createAccount();
  }


}
