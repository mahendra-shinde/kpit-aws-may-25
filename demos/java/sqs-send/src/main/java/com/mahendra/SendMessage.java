package com.mahendra;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;


public class SendMessage {
    public static void main(String[] args) {
        String queueUrl = "https://sqs.us-east-1.amazonaws.com/890756660068/que1"; // Replace with your queue URL

        SqsClient sqsClient = SqsClient.builder().build();

        SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
            .queueUrl(queueUrl)
            .messageBody("Hello from Java SQS client!")
            .build();

        SendMessageResponse response = sqsClient.sendMessage(sendMsgRequest);

        System.out.println("Message sent with ID: " + response.messageId());

        sqsClient.close();
    }
}