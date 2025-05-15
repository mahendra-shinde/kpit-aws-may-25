# Tutorial: Amazon EC2 Instances

## Introduction
Amazon Elastic Compute Cloud (EC2) is a web service that provides resizable compute capacity in the cloud. It is designed to make web-scale cloud computing easier for developers. With EC2, you can launch virtual servers, configure security and networking, and manage storage.

## Steps to Create an EC2 Instance

### Step 1: Log in to the AWS Management Console
1. Navigate to [AWS Management Console](https://aws.amazon.com/console/).
2. Log in with your AWS credentials.

### Step 2: Navigate to the EC2 Dashboard
1. In the AWS Management Console, search for "EC2" in the search bar.
2. Click on **EC2** to open the EC2 Dashboard.

### Step 3: Launch an Instance
1. Click on the **Launch Instance** button.
2. Provide a name for your instance.

### Step 4: Choose an Amazon Machine Image (AMI)
1. Select an AMI from the list (e.g., Amazon Linux, Ubuntu, Windows Server).
2. Ensure the AMI meets your application requirements.

### Step 5: Choose an Instance Type
1. Select an instance type based on your compute and memory needs (e.g., t2.micro for free tier eligibility).
2. Click **Next**.

### Step 6: Configure Instance Details
1. Specify the number of instances.
2. Configure networking settings, such as VPC and subnet.
3. Leave other settings as default for now and click **Next**.

### Step 7: Add Storage
1. Specify the storage volume size and type.
2. Click **Next**.

### Step 8: Add Tags
1. Add tags to organize your resources (e.g., Key: Name, Value: MyInstance).
2. Click **Next**.

### Step 9: Configure Security Group
1. Create a new security group or select an existing one.
2. Add rules to allow specific traffic (e.g., SSH for port 22).
3. Click **Review and Launch**.

### Step 10: Review and Launch
1. Review your instance configuration.
2. Click **Launch**.
3. Select or create a key pair for SSH access and click **Launch Instances**.

### Step 11: Access Your Instance
1. Once the instance is running, note the public IP address.
2. Use an SSH client to connect to the instance (e.g., `ssh -i your-key.pem ec2-user@<public-ip>`).

## Summary
In this tutorial, you learned how to create an EC2 instance in AWS. EC2 provides scalable compute capacity, enabling you to deploy applications quickly and efficiently. Explore additional features like auto-scaling and load balancing to optimize your cloud infrastructure.
