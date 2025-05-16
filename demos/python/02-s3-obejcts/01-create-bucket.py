import boto3

def create_bucket(bucket_name, region):
    """
    Create an S3 bucket in a specified region.
    
    :param bucket_name: Name of the bucket to create
    :param region: AWS region where the bucket will be created
    """
    # Create an S3 client
    s3_client = boto3.client('s3', region)

    # Create the bucket
    try:
        resp = s3_client.create_bucket(
            Bucket=bucket_name
        )
        print(resp)
        print(f"Bucket {bucket_name} created successfully in {region} region.")
    except Exception as e:
        print(f"Error creating bucket: {e}")

def list_buckets(region='us-east-1'):
    """
    List all S3 buckets in the account.
    """
    # Create an S3 client
    s3_client = boto3.client('s3', region)

    # List the buckets
    try:
        response = s3_client.list_buckets()
        print("Existing buckets:")
        for bucket in response['Buckets']:
            print(f"  {bucket['Name']}")
    except Exception as e:
        print(f"Error listing buckets: {e}")


list_buckets('us-east-1')
create_bucket('mybuck13499456', 'us-east-1')
list_buckets('us-east-1')