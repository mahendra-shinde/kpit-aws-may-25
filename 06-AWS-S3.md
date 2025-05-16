# Amazon S3 Tutorial

Amazon Simple Storage Service (Amazon S3) is a scalable object storage service used for storing and retrieving any amount of data from anywhere on the web. This tutorial covers the basics of S3, including creating buckets, uploading and downloading files, and managing permissions.

## 1. Key Concepts
- **Bucket**: A container for storing objects (files).
- **Object**: A file and its metadata stored in a bucket.
- **Key**: The unique identifier for an object within a bucket.

## 2. Creating an S3 Bucket
You can create a bucket using the AWS Management Console, AWS CLI, or SDKs (like Boto3 for Python).

### Using AWS CLI
```sh
aws s3 mb s3://your-bucket-name
```

### Using Python (Boto3)
```python
import boto3
s3 = boto3.client('s3')
s3.create_bucket(Bucket='your-bucket-name')
```

## 3. Uploading Files to S3

### Using AWS CLI
```sh
aws s3 cp localfile.txt s3://your-bucket-name/
```

### Using Python (Boto3)
```python
import boto3
s3 = boto3.client('s3')
s3.upload_file('localfile.txt', 'your-bucket-name', 'localfile.txt')
```

## 4. Downloading Files from S3

### Using AWS CLI
```sh
aws s3 cp s3://your-bucket-name/localfile.txt ./
```

### Using Python (Boto3)
```python
import boto3
s3 = boto3.client('s3')
s3.download_file('your-bucket-name', 'localfile.txt', 'downloaded.txt')
```

## 5. Listing Buckets and Objects

### List Buckets
```python
import boto3
s3 = boto3.client('s3')
response = s3.list_buckets()
for bucket in response['Buckets']:
    print(bucket['Name'])
```

### List Objects in a Bucket
```python
import boto3
s3 = boto3.client('s3')
response = s3.list_objects_v2(Bucket='your-bucket-name')
for obj in response.get('Contents', []):
    print(obj['Key'])
```

## 6. Deleting Objects and Buckets

### Delete an Object
```python
s3.delete_object(Bucket='your-bucket-name', Key='localfile.txt')
```

### Delete a Bucket (must be empty)
```python
s3.delete_bucket(Bucket='your-bucket-name')
```

## 7. Permissions and Policies
To upload, download, or manage S3 resources, your IAM user or role must have the appropriate permissions. Example policy for full S3 access:

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": "s3:*",
      "Resource": [
        "arn:aws:s3:::your-bucket-name",
        "arn:aws:s3:::your-bucket-name/*"
      ]
    }
  ]
}
```

## 8. S3 Storage Classes
Amazon S3 offers multiple storage classes to help you optimize cost and performance:
- **S3 Standard**: General-purpose storage for frequently accessed data.
- **S3 Intelligent-Tiering**: Automatically moves data between two access tiers when access patterns change.
- **S3 Standard-IA (Infrequent Access)**: For data accessed less frequently but requires rapid access.
- **S3 One Zone-IA**: Lower-cost option for infrequently accessed data that does not require multiple Availability Zone resilience.
- **S3 Glacier**: Low-cost storage for data archiving, with retrieval times from minutes to hours.
- **S3 Glacier Deep Archive**: Lowest-cost storage for long-term retention, with retrieval times of 12 hours or more.

You can specify the storage class when uploading an object:
```sh
aws s3 cp localfile.txt s3://your-bucket-name/ --storage-class STANDARD_IA
```

## 9. S3 Replication
S3 Replication automatically copies objects across buckets in the same or different AWS regions for compliance, lower latency, or disaster recovery.
- **Cross-Region Replication (CRR)**: Replicates objects to a bucket in another AWS region.
- **Same-Region Replication (SRR)**: Replicates objects to another bucket in the same region.

Replication requires enabling versioning on both source and destination buckets and setting up a replication rule in the S3 console or via the AWS CLI.

## 10. S3 Lifecycle Rules
Lifecycle rules help you automate the transition of objects between storage classes and the expiration (deletion) of objects to optimize costs.

Example: Transition objects to S3 Glacier after 30 days and delete after 365 days.

### Using AWS CLI (JSON policy example)
```json
{
  "Rules": [
    {
      "ID": "Move to Glacier and Expire",
      "Prefix": "",
      "Status": "Enabled",
      "Transitions": [
        {
          "Days": 30,
          "StorageClass": "GLACIER"
        }
      ],
      "Expiration": {
        "Days": 365
      }
    }
  ]
}
```
You can add lifecycle rules in the S3 console under the Management tab or use the AWS CLI:
```sh
aws s3api put-bucket-lifecycle-configuration --bucket your-bucket-name --lifecycle-configuration file://lifecycle.json
```


## 11. Best Practices
- Enable versioning to protect against accidental deletions.
- Use bucket policies to control access.
- Enable server-side encryption for sensitive data.
- Set up lifecycle rules to manage storage costs.

## 12. References
- [Amazon S3 Documentation](https://docs.aws.amazon.com/s3/index.html)
- [Boto3 S3 Documentation](https://boto3.amazonaws.com/v1/documentation/api/latest/reference/services/s3.html)