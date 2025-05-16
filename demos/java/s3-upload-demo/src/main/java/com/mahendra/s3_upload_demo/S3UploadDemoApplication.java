package com.mahendra.s3_upload_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Paths;

import org.springframework.boot.CommandLineRunner;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@SpringBootApplication
public class S3UploadDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(S3UploadDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("S3 Upload Demo Application has started.");
		S3Client client = S3Client.builder()
				.region(Region.US_EAST_1).build();
		String bucketName = "buck37tr76tr";
		
		// upload object to s3 bucket
		String objectKey = "sample.md";
		String filePath = "D:/git/kpit-aws-may-25/demos/python/02-s3-obejcts/sample.md";
		client.putObject(b -> b.bucket(bucketName)
								.key(objectKey)
								.contentType("text/plain"),
					RequestBody.fromFile(Paths.get(filePath)));	
	
		System.out.println("File uploaded to S3 bucket successfully.");
	}

}
