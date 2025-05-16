import boto3

def download_file_from_s3(bucket_name, object_name, file_path):
    """
    Download a file from an S3 bucket.
    
    :param bucket_name: Name of the bucket to download from
    :param object_name: S3 object name
    :param file_path: Path to save the downloaded file
    """
    # Create an S3 client
    s3_client = boto3.client('s3')

    # Download the file
    try:
        s3_client.download_file(bucket_name, object_name, file_path)
        print(f"File {object_name} downloaded from bucket {bucket_name} to {file_path}.")
    except Exception as e:
        print(f"Error downloading file: {e}")

print('Downloading file from S3 bucket')
download_file_from_s3('buck37tr76tr', 'sample.md', './sample_downloaded.md')