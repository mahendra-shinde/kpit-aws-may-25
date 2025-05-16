# Upload a heavy file using Async method to s3 bucket
import asyncio
import aiofiles
import aioboto3
import os
from botocore.exceptions import ClientError
from botocore.config import Config
from tqdm import tqdm
from concurrent.futures import ThreadPoolExecutor
from botocore.client import Config
# Create a custom configuration for the S3 client
s3_config = Config(
    region_name='us-east-1',
    signature_version='s3v4',
    retries={
        'max_attempts': 10,
        'mode': 'adaptive'
    }
)
async def upload_file_to_s3_async(bucket_name, file_path, object_name=None):
    """
    Upload a file to an S3 bucket asynchronously.
    
    :param bucket_name: Name of the bucket to upload to
    :param file_path: Path to the file to upload
    :param object_name: S3 object name. If not specified, file_path is used
    """
    # Create an S3 client
    session = aioboto3.Session()
    async with session.client('s3', config=s3_config) as s3_client:
        try:
            # Get the file size
            file_size = os.path.getsize(file_path)
            # Create a progress bar
            progress_bar = tqdm(total=file_size, unit='B', unit_scale=True, desc=file_path)
            
            async with aiofiles.open(file_path, 'rb') as f:
                # Upload the file in chunks
                async def upload_chunk(chunk):
                    await s3_client.upload_fileobj(chunk, bucket_name, object_name or file_path)
                    progress_bar.update(len(chunk))
                
                chunk_size = 1024 * 1024  # 1 MB
                while True:
                    chunk = await f.read(chunk_size)
                    if not chunk:
                        break
                    await upload_chunk(chunk)
            
            progress_bar.close()
            print(f"File {file_path} uploaded to bucket {bucket_name}.")
        except ClientError as e:
            print(f"Error uploading file: {e}")
        except Exception as e:
            print(f"Error uploading file: {e}")

def main():
    bucket_name = 'buck37tr76tr'
    file_path = './sample.md'
    object_name = 'sample.md'  # Optional, if not specified, file_path is used

    loop = asyncio.get_event_loop()
    loop.run_until_complete(upload_file_to_s3_async(bucket_name, file_path, object_name))
