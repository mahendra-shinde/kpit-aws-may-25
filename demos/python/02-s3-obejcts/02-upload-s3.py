import boto3

def upload_file_to_s3(bucket_name, file_path, object_name=None):
    """
    Upload a file to an S3 bucket.
    
    :param bucket_name: Name of the bucket to upload to
    :param file_path: Path to the file to upload
    :param object_name: S3 object name. If not specified, file_path is used
    """
    # Create an S3 client
    s3_client = boto3.client('s3')

    # Upload the file
    try:
        s3_client.upload_file(file_path, bucket_name, object_name or file_path, ExtraArgs={'ContentType': 'text/markdown'})
        print(f"File {file_path} uploaded to bucket {bucket_name}.")
    except Exception as e:
        print(f"Error uploading file: {e}")

upload_file_to_s3('buck37tr76tr','D:/git/kpit-aws-may-25/demos/python/02-s3-obejcts/sample.md','sample.md')
print('File uploaded successfully')