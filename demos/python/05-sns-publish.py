import boto3

def publish_sns_message():
    # Create an SNS client
    sns = boto3.client('sns',region_name='us-east-1')

    # Publish a message to the SNS topic
    response = sns.publish(
        TopicArn='arn:aws:sns:us-east-1:890756660068:news',
        Message='Hello, this is a test message!',
        Subject='Test Message'
    )

    # Print the response
    print("Message ID:", response['MessageId'])

if __name__ == "__main__":
    publish_sns_message()