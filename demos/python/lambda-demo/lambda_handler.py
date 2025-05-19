import logging

def lambda_handler(event, context):
    logger = logging.getLogger()
    logger.setLevel(logging.INFO)

    # Assuming the event is an S3Event from AWS Lambda
    record = event['Records'][0]
    s3_object = record['s3']['object']
    key = s3_object['key']
    size = s3_object.get('size', 0)

    logger.info("Starting Lambda function ....")
    logger.info(f"Processing object: {key}")
    logger.info(f"Object size: {size}")
    logger.info("End of function ....")

    return f"Hello from AWS Lambda! Input: {event}"
