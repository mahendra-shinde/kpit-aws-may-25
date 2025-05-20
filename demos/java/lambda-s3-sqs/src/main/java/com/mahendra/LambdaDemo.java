package com.mahendra;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class LambdaDemo implements RequestHandler<SQSEvent, String> {

    private final AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();

    public String handleRequest(SQSEvent event, Context context) {
        event.getRecords().forEach(record -> {
            String student = record.getMessageAttributes().get("student").getStringValue();
            String course = record.getMessageAttributes().get("course").getStringValue();
            StringBuilder content = new StringBuilder().append("Mr/Ms ")
                                        .append(student).append(" has completed successfully ")
                                        .append(course).append(" course");
            
            String objectKey = student + " " + course + ".txt";            
            s3Client.putObject("certs34682746", objectKey, content.toString());
            context.getLogger().log("Uploaded file: " + objectKey);
        });
        return "done";
    }
    
}
