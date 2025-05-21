import boto3
from botocore.exceptions import ClientError
from boto3.dynamodb.conditions import Key

# Initialize a DynamoDB resource using default AWS credentials and region
try:
    dynamodb = boto3.resource('dynamodb', region_name='us-east-1')
    table = dynamodb.Table('contacts')
    
    # Scan the table to get all items
    #response = table.scan()
    response = table.query(
        KeyConditionExpression=Key('contactId').eq(102)
    )
    items = response.get('Items', [])
    
    print(f"Found {len(items)} items in 'contacts' table:")
    for item in items:
        print(item)
except ClientError as e:
    print(f"An error occurred: {e.response['Error']['Message']}")
except Exception as e:
    print(f"Unexpected error: {e}")

# add new record in table
try:
    table.put_item(
        Item={
            'contactId': 103,
            'firstname': 'Rajiv',
            'lastname': 'Bhatia',
            'email': 'akki@xyz.com',
            'phone': {
                "home": "1234567890",
                "office": "0987654321"
            }
        })
except ClientError as e:
    print(f"An error occurred: {e.response['Error']['Message']}")
except Exception as e:
    print(f"Unexpected error: {e}")

