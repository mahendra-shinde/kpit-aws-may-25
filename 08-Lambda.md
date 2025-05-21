# AWS Lambda Functions Tutorial

AWS Lambda is a serverless compute service that lets you run code without provisioning or managing servers. You simply upload your code as a function, and Lambda takes care of everything required to run and scale your code.

1. Execution Timeout Limit
    - Default time for execution is 3 seconds, minimum is 1 second.
    - Maximum execution time is 15 minutes (900 seconds) only.

2. Language Support

    AWS Lambda supports Java, Python, Node.js, and more.
    C / C++ is not natively supported.

3. Pricing Model

    Pay-as-you-go pricing based on:

    - Number of requests
    - Execution duration (rounded up to the nearest millisecond)

4. Event Sources / Triggers

    Lambda can be triggered by several AWS services.

    Examples include:

    - Amazon S3
    - API Gateway
    - CloudWatch Events
    - DynamoDB Streams, etc.

---

## Creating a Lambda Function

### 1. Authoring the Function
You can write Lambda functions in various languages, including Java, Python, Node.js, and more.

### 2. Deploying the Function
- Use the AWS Console, AWS CLI, or Infrastructure as Code tools (like CloudFormation, SAM, or Terraform).
- Package your code and dependencies as a deployment package (ZIP or JAR for Java).

---

# Demo 1: Lambda Function in Java

### Project Structure
See `demos/java/lambda-demo/` for a sample Maven project.

### Sample Handler (Java)
```java
package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class HelloLambda implements RequestHandler<String, String> {
    @Override
    public String handleRequest(String input, Context context) {
        return "Hello from Lambda, " + input + "!";
    }
}
```

### Steps to Deploy
1. **Build the JAR**:
   ```sh
   mvn clean package
   ```
2. **Upload the JAR** to AWS Lambda (Console or CLI).
3. **Set Handler**: `com.example.HelloLambda::handleRequest`
4. **Test**: Use the Lambda console to invoke with a sample input.

---

# Demo 2: Lambda Function in Python

See `demos/python/lambda-demo/lambda_handler.py` for a sample.

### Sample Handler (Python)
```python
def lambda_handler(event, context):
    name = event.get('name', 'World')
    return {
        'statusCode': 200,
        'body': f"Hello from Lambda, {name}!"
    }
```

### Steps to Deploy
1. **Zip the code**:
   ```sh
   zip function.zip lambda_handler.py
   ```
2. **Upload the ZIP** to AWS Lambda (Console or CLI).
3. **Set Handler**: `lambda_handler.lambda_handler`
4. **Test**: Use the Lambda console to invoke with a sample event:
   ```json
   { "name": "Alice" }
   ```

---

## Useful Resources
- [AWS Lambda Documentation](https://docs.aws.amazon.com/lambda/latest/dg/welcome.html)
- [AWS Lambda Java Programming Model](https://docs.aws.amazon.com/lambda/latest/dg/java-handler.html)
- [AWS Lambda Python Programming Model](https://docs.aws.amazon.com/lambda/latest/dg/python-handler.html)

---

Feel free to explore the sample projects in the `demos/` directory for hands-on practice!