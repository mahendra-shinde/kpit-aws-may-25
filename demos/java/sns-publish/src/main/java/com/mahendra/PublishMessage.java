package com.mahendra;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

public class PublishMessage {

    public static void main(String[] args) {
        
        String topicArn = "arn:aws:sns:us-east-1:890756660068:news"; // Replace with your SNS topic ARN
        String message = "Hello from Java SNS client!";

        SnsClient snsClient = SnsClient.builder().build();

        try {
            PublishRequest publishRequest = PublishRequest.builder()
                    .topicArn(topicArn)
                    .message(message)
                    .build();

            PublishResponse publishResponse = snsClient.publish(publishRequest);
            System.out.println("Message sent with ID: " + publishResponse.messageId());
        } catch (SnsException e) {
            System.err.println("Error sending message: " + e.awsErrorDetails().errorMessage());
        } finally {
            snsClient.close();
        }

    }
}
