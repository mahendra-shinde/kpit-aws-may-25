import json
import boto3

def lambda_handler(event, context):
    message = event['Records'][0]['body']
    student = event['Records'][0]['messageAttributes']['student']['stringValue']
    course = event['Records'][0]['messageAttributes']['course']['stringValue']
    cert_message = f"Mr/Ms {student} has successfuly completed {course} course."
    s3client = boto3.client('s3')
    s3client.put_object(Body=cert_message, Bucket='certs34682746', Key=f'{student}-{course}.txt')
