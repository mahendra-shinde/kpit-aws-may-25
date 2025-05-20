package com.mahendra;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

public class ReceiveMessage {
    public static void main(String[] args) {
        String queueUrl = "https://sqs.us-east-1.amazonaws.com/890756660068/que1"; // Replace with your queue URL

        SqsClient sqsClient = SqsClient.builder().build();

        ReceiveMessageRequest receiveMsgRequest = ReceiveMessageRequest.builder()
            .queueUrl(queueUrl)
            .maxNumberOfMessages(5)
            .waitTimeSeconds(10)
            .build();

        ReceiveMessageResponse response = sqsClient.receiveMessage(receiveMsgRequest);

        for (Message message : response.messages()) {
            System.out.println("Received message: " + message.body());
        }

        sqsClient.close();
    }
}
