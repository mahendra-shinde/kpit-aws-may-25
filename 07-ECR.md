# Amazon Elastic Container Registry (ECR) Tutorial

Amazon ECR is a fully managed Docker container registry that makes it easy to store, manage, and deploy container images.

## Prerequisites

- AWS account
- AWS CLI installed and configured
- Docker installed

## 1. Create an ECR Repository

```sh
aws ecr create-repository --repository-name my-app-repo
```

Note the `repositoryUri` from the output.

## 2. Authenticate Docker to ECR

```sh
aws ecr get-login-password | docker login --username AWS --password-stdin <aws_account_id>.dkr.ecr.<region>.amazonaws.com
```

Replace `<aws_account_id>` and `<region>` with your values.

## 3. Build Your Docker Image

```sh
docker build -t my-app .
```

## 4. Tag the Image for ECR

```sh
docker tag my-app:latest <repositoryUri>:latest
```

## 5. Push the Image to ECR

```sh
docker push <repositoryUri>:latest
```

## 6. Pull the Image from ECR (Optional)

```sh
docker pull <repositoryUri>:latest
```

## 7. Enable Image Scanning

ECR supports image scanning to detect vulnerabilities. Enable scanning on push:

```sh
aws ecr put-image-scanning-configuration --repository-name my-app-repo --image-scanning-configuration scanOnPush=true
```

To scan an existing image:

```sh
aws ecr start-image-scan --repository-name my-app-repo --image-id imageTag=latest
```

## 8. Set Lifecycle Policies

Lifecycle policies help manage old images and save storage costs. Example: keep only the last 10 images.

Create a file `lifecycle-policy.json`:

```json
{
  "rules": [
    {
      "rulePriority": 1,
      "description": "Keep last 10 images",
      "selection": {
        "tagStatus": "any",
        "countType": "imageCountMoreThan",
        "countNumber": 10
      },
      "action": { "type": "expire" }
    }
  ]
}
```

Apply the policy:

```sh
aws ecr put-lifecycle-policy --repository-name my-app-repo --lifecycle-policy-text file://lifecycle-policy.json
```

## 9. Set Permissions (Policies)

You can control access to your ECR repository using resource-based policies. Example: allow another AWS account to pull images.

Create a file `ecr-policy.json`:

```json
{
  "Version": "2008-10-17",
  "Statement": [
    {
      "Sid": "AllowPull",
      "Effect": "Allow",
      "Principal": { "AWS": "arn:aws:iam::<other_account_id>:root" },
      "Action": ["ecr:GetDownloadUrlForLayer", "ecr:BatchGetImage", "ecr:BatchCheckLayerAvailability"]
    }
  ]
}
```

Apply the policy:

```sh
aws ecr set-repository-policy --repository-name my-app-repo --policy-text file://ecr-policy.json
```

## 10. Clean Up (Optional)

To delete the repository and all images:

```sh
aws ecr delete-repository --repository-name my-app-repo --force
```

## References

- [AWS ECR Documentation](https://docs.aws.amazon.com/AmazonECR/latest/userguide/what-is-ecr.html)
- [ECR Lifecycle Policies](https://docs.aws.amazon.com/AmazonECR/latest/userguide/LifecyclePolicies.html)
- [ECR Image Scanning](https://docs.aws.amazon.com/AmazonECR/latest/userguide/image-scanning.html)
- [ECR Repository Policies](https://docs.aws.amazon.com/AmazonECR/latest/userguide/repository-policies.html)
