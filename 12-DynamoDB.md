# AWS DynamoDB Tutorial

## What is DynamoDB?

Amazon DynamoDB is a fully managed NoSQL database service that provides fast and predictable performance with seamless scalability. It is a *key-value* and *document* database that delivers single-digit millisecond performance at any scale.

---

## Key Features
- **Fully Managed**: No server management required.
- **Scalable**: Handles large amounts of data and high request rates.
- **Flexible Data Model**: Supports key-value and document data structures.
- **Integrated Security**: Encryption at rest, fine-grained access control.
- **Event-Driven**: Integrates with AWS Lambda for triggers.
- **Low-latency**:  delivers single digit millisecond latency.

## Ideal Workload Types

DynamoDB is best suited for:
- Applications requiring consistent, low-latency data access at any scale.
- Workloads with high request rates (e.g., gaming, IoT, mobile apps).
- Scenarios needing flexible, schema-less data models.
- Use cases with unpredictable or rapidly growing workloads.
- Applications needing seamless scaling without manual intervention.
- Systems requiring high availability and durability.
- Event-driven architectures integrating with AWS Lambda.
- Real-time analytics and session management.

---
### Caching with DAX

* Amazon DynamoDB Accelerator (DAX) provides fully managed, in-memory caching.
* Reduces response times from milliseconds to microseconds for read-heavy applications.

### Read Consistency Models

- Eventually consistent reads are the default, offering better performance.
- Strongly consistent reads are available but must be explicitly requested.

### Data Types

DynamoDB supports:

- String
- Number
- Boolean
- Binary
- Map
- List
- Null


### Primary Key Structure

Each item in a DynamoDB table is uniquely identified using:

- A partition key, or
- A composite key (partition key + sort key)


## Core Concepts
- **Table**: Collection of data.
- **Item**: A single data record in a table (like a row).
- **Attribute**: A single data element on an item (like a column).
- **Primary Key**: Uniquely identifies each item (Partition key or Partition + Sort key).

---

## Creating a DynamoDB Table (AWS Console)
1. Go to the DynamoDB service in AWS Console.
2. Click **Create table**.
3. Enter a **Table name** (e.g., `Users`).
4. Set a **Primary key** (e.g., `UserId` as Partition key).
5. Configure settings as needed (provisioned capacity, encryption, etc.).
6. Click **Create table**.

---

## Basic Operations (Python Example)
Install the AWS SDK for Python (boto3):
```bash
pip install boto3
```

### Create a Table
```python
import boto3

dynamodb = boto3.resource('dynamodb')
table = dynamodb.create_table(
    TableName='Users',
    KeySchema=[
        {'AttributeName': 'UserId', 'KeyType': 'HASH'}
    ],
    AttributeDefinitions=[
        {'AttributeName': 'UserId', 'AttributeType': 'S'}
    ],
    ProvisionedThroughput={
        'ReadCapacityUnits': 5,
        'WriteCapacityUnits': 5
    }
)
table.wait_until_exists()
```

### Put (Insert) an Item
```python
table = dynamodb.Table('Users')
table.put_item(
    Item={
        'UserId': '123',
        'Name': 'Alice',
        'Email': 'alice@example.com'
    }
)
```

### Get an Item
```python
response = table.get_item(Key={'UserId': '123'})
item = response.get('Item')
print(item)
```

### Query Items
```python
response = table.query(
    KeyConditionExpression=boto3.dynamodb.conditions.Key('UserId').eq('123')
)
for item in response['Items']:
    print(item)
```

### Delete an Item
```python
table.delete_item(Key={'UserId': '123'})
```

---

## Best Practices
- Use appropriate primary keys for access patterns.
- Use indexes (GSI, LSI) for flexible queries.
- Enable auto-scaling for throughput.
- Use DynamoDB Streams for change data capture.

---

## References
- [AWS DynamoDB Documentation](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/)
- [Boto3 DynamoDB Docs](https://boto3.amazonaws.com/v1/documentation/api/latest/reference/services/dynamodb.html)

---
