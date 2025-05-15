# AWS IAM (Identity and Access Management)

AWS IAM is a web service that helps you securely control access to AWS resources. It allows you to manage users, groups, roles, and their permissions to ensure that only authorized entities can access specific resources.

## Users
Users in IAM represent individual people or services that need access to AWS resources. Each user is assigned a unique identity and can have specific permissions to perform actions on AWS services.

## User Credentials
User credentials are used to authenticate users. These include:
- **Access Keys**: Used for programmatic access via the AWS CLI or SDKs.
- **Passwords**: Used for access to the AWS Management Console.

## Groups
Groups are collections of IAM users. Permissions can be assigned to a group, and all users in the group inherit those permissions. This simplifies management by allowing you to assign permissions at the group level instead of individually.

## Permissions
Permissions define what actions a user or group can perform on specific AWS resources. Permissions are granted using IAM policies, which are JSON documents that specify allowed or denied actions.


