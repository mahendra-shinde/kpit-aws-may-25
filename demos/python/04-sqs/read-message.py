import boto3

# Replace with your SQS queue URL
QUEUE_URL = 'https://sqs.us-east-1.amazonaws.com/890756660068/que1'

# Create SQS client
sqs = boto3.client('sqs',region_name='us-east-1')

def receive_messages(queue_url, max_messages=2, wait_time=5):
    response = sqs.receive_message(
        QueueUrl=queue_url,
        MaxNumberOfMessages=max_messages,
        WaitTimeSeconds=wait_time
    )
    messages = response.get('Messages', [])
    for msg in messages:
        print(f"Message ID: {msg['MessageId']}")
        print(f"Body: {msg['Body']}")
        # Optionally delete the message after processing
        sqs.delete_message(QueueUrl=queue_url, ReceiptHandle=msg['ReceiptHandle'])

if __name__ == "__main__":
    receive_messages(QUEUE_URL)