package com.mahendra.s3_demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@SpringBootApplication
public class S3DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(S3DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// This method is intentionally left empty
		// You can add any startup logic here if needed
		System.out.println("S3 Demo Application started successfully!");
		S3Client client = S3Client.builder().
						// credentialsProvider(StaticCredentialsProvider.create(
						// AwsBasicCredentials.create("ACCESSKEYID","ACCESSSECRET"))).
								region(Region.US_EAST_1).
								build();
		System.out.println("S3 Client created successfully!");
		client.createBucket(b -> b.bucket("buck37tr76tr"));
		System.out.println("Bucket created successfully!");
		System.out.println("List of buckets:");
		client.listBuckets().buckets().forEach(b -> {
			System.out.println(b.name());
		});
		client.close();
		
	}
}
