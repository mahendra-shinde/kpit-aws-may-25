package com.mahendra;

import java.util.logging.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3ObjectEntity;

/**
 * This is a simple AWS Lambda function handler that processes input and returns a greeting message.
 * It implements the RequestHandler interface to handle incoming requests.
 */
public class LambdaHandler implements RequestHandler<S3Event, String> {

	private static final Logger log = Logger.getLogger(LambdaHandler.class.getName());
	@Override
	public String handleRequest(S3Event input, Context context) {
		
		S3ObjectEntity obj = input.getRecords().get(0).getS3().getObject();
		log.info(() -> "Starting Lambda function ....");
		log.info(() -> "Processing object: " + obj.getKey());
		log.info(() -> "Object size: " + obj.getSizeAsLong());
		

		log.info(() -> "End of function ....");
		return "Hello from AWS Lambda! Input: " + input;
	}
	
}