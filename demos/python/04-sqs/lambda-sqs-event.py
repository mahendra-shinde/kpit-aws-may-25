import json
import boto3

def lambda_handler(event, context):
    # Get the Message from the event
    message = event['Records'][0]['body']
    messageId = event['Records'][0]['messageId']
    # Handler is for deleting the message from the queue
    receiptHandle = event['Records'][0]['receiptHandle']
    start_date = event['Records'][0]['messageAttributes']['StartDate']['stringValue']
    end_date = event['Records'][0]['messageAttributes']['EndDate']['stringValue']
    print("StartDate: " + start_date)
    print("EndDate: " + end_date)
    print("MessageId: " + messageId)
    print("ReceiptHandle: " + receiptHandle)
    print("Message: " + message)