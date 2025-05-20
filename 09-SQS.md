# AWS SQS Tutorial

Amazon Simple Queue Service (SQS) is a fully managed message queuing service that enables you to decouple and scale microservices, distributed systems, and serverless applications. SQS offers two types of message queues: Standard (best-effort ordering, at-least-once delivery) and FIFO (exactly-once processing, first-in-first-out delivery).

## Key Concepts
- **Queue**: Stores messages until they are processed and deleted.
- **Message**: Data sent between distributed application components.
- **Producer**: Sends messages to the queue.
- **Consumer**: Retrieves and processes messages from the queue.

---

# 1. Creating an SQS Queue

You can create a queue using the AWS Console, AWS CLI, or SDKs.

## Using AWS CLI
```sh
aws sqs create-queue --queue-name MyQueue
```

---

# 2. Java Example: Send and Receive Messages

## Maven Dependency
Add the following to your `pom.xml`:
```xml
<dependency>
    <groupId>software.amazon.awssdk</groupId>
    <artifactId>sqs</artifactId>
    <version>2.25.0</version>
</dependency>
```

## Send Message
```java
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class SQSSendExample {
    public static void main(String[] args) {
        SqsClient sqsClient = SqsClient.create();
        String queueUrl = "<YOUR_QUEUE_URL>";
        SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
            .queueUrl(queueUrl)
            .messageBody("Hello from Java!")
            .build();
        sqsClient.sendMessage(sendMsgRequest);
        System.out.println("Message sent.");
    }
}
```

## Receive Message
```java
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;

ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
    .queueUrl(queueUrl)
    .maxNumberOfMessages(1)
    .build();
List<Message> messages = sqsClient.receiveMessage(receiveRequest).messages();
for (Message m : messages) {
    System.out.println("Received: " + m.body());
}
```

---

# 3. Python Example: Send and Receive Messages

## Install Boto3
```sh
pip install boto3
```

## Send Message
```python
import boto3
sqs = boto3.client('sqs')
queue_url = '<YOUR_QUEUE_URL>'
response = sqs.send_message(
    QueueUrl=queue_url,
    MessageBody='Hello from Python!'
)
print('Message ID:', response['MessageId'])
```

## Receive Message
```python
response = sqs.receive_message(
    QueueUrl=queue_url,
    MaxNumberOfMessages=1
)
for message in response.get('Messages', []):
    print('Received:', message['Body'])
    # Delete message after processing
    sqs.delete_message(
        QueueUrl=queue_url,
        ReceiptHandle=message['ReceiptHandle']
    )
```

---

# 4. Useful Tips
- Always delete messages after processing to prevent reprocessing.
- Use environment variables or AWS credentials profiles for authentication.
- For FIFO queues, use the `.fifo` suffix and provide a `MessageGroupId`.

---

# 5. References
- [AWS SQS Documentation](https://docs.aws.amazon.com/sqs/)
- [AWS SDK for Java v2](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-sqs.html)
- [Boto3 SQS Docs](https://boto3.amazonaws.com/v1/documentation/api/latest/reference/services/sqs.html)

