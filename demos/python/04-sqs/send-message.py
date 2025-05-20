import boto3

# Create SQS client
sqs = boto3.client('sqs', region_name='us-east-1')

# Replace with your SQS queue URL
queue_url = 'https://sqs.us-east-1.amazonaws.com/890756660068/que1'

# Send message to SQS queue
response = sqs.send_message(
    QueueUrl=queue_url,
    MessageBody='Hello from Python!'
)

print('Message ID:', response['MessageId'])