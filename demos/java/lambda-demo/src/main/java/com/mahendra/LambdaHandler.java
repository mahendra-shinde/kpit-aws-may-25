package com.mahendra;

import java.util.Map;
import java.util.logging.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * This is a simple AWS Lambda function handler that processes input and returns a greeting message.
 * It implements the RequestHandler interface to handle incoming requests.
 */
public class LambdaHandler implements RequestHandler<Map<String, String>, String> {

	private static final Logger log = Logger.getLogger(LambdaHandler.class.getName());

	@Override
	public String handleRequest(Map<String, String> input, Context context) {
		
		log.info(() -> "Starting Lambda function ....");
		log.info(() -> "Processing input: " + input);
		log.info(() -> "End of function ....");
		return "Hello from AWS Lambda! Input: " + input;
	}
	
}