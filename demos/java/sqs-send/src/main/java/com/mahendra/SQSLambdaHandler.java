package com.mahendra;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

/**
 * AWS Lambda handler for processing SQS events.
 */
/// Lambda function handler
/// com.mahendra.SQSLambdaHandler::handleRequest
public class SQSLambdaHandler implements RequestHandler<SQSEvent, String> {
    @Override
    public String handleRequest(SQSEvent event, Context context) {
        // Get the first record from the SQS event
        SQSEvent.SQSMessage message = event.getRecords().get(0);
        String body = message.getBody();
        String messageId = message.getMessageId();
        String receiptHandle = message.getReceiptHandle();
        String startDate = message.getAttributes().get("StartDate");
        String endDate = message.getAttributes().get("EndDate");

        System.out.println("StartDate: " + startDate);
        System.out.println("EndDate: " + endDate);
        System.out.println("MessageId: " + messageId);
        System.out.println("ReceiptHandle: " + receiptHandle);
        System.out.println("Message: " + body);

        return "Processed SQS message: " + messageId;
    }
}
