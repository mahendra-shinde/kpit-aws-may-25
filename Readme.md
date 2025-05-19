Certainly! Here's a **summary of AWS Fundamentals** that covers all the topics referenced in your MCQs. This summary is ideal for learners preparing for AWS basics or cloud certification courses like AWS Cloud Practitioner or AWS Certified Solutions Architect – Associate.

---

## **AWS Fundamentals Summary**

### 1. **Cloud Computing Basics**

Cloud computing is the on-demand delivery of IT resources via the internet with a pay-as-you-go pricing model. It eliminates the need for owning and maintaining physical data centers and servers.

#### **Benefits of Cloud Computing**

* **Scalability:** Resources can be increased or decreased as needed.
* **Cost-Efficiency:** Pay only for what you use.
* **High Availability:** Cloud platforms offer built-in redundancy.
* **Agility:** Faster provisioning of resources.

---

### 2. **Cloud Deployment Models**

* **Public Cloud:** Services offered over the public internet (e.g., AWS, Azure).
* **Private Cloud:** Used exclusively by a single organization.
* **Hybrid Cloud:** A combination of public and private environments.

---

### 3. **Cloud Service Models**

* **IaaS (Infrastructure as a Service):** Provides virtualized computing resources (e.g., AWS EC2).
* **PaaS (Platform as a Service):** Provides development platforms and tools (e.g., AWS Elastic Beanstalk).
* **SaaS (Software as a Service):** Delivers software applications over the internet (e.g., Google Workspace).

---

### 4. **Amazon VPC (Virtual Private Cloud)**

Amazon VPC allows users to create a logically isolated network in the AWS cloud.

#### **Key Components:**

* **Subnets:** Segments within a VPC (public/private).
* **Internet Gateway:** Enables internet access to public subnets.
* **NAT Gateway:** Allows private subnets to access the internet.
* **Route Tables & Network ACLs:** Control traffic routing and security.

---

### 5. **Amazon EC2 (Elastic Compute Cloud)**

EC2 provides scalable computing capacity in the cloud.

#### **Key Concepts:**

* **Instance Types:** Varying compute/memory optimized options.
* **AMI (Amazon Machine Image):** A pre-configured template used to launch instances.
* **Pricing Models:** On-Demand, Reserved, Spot, Free Tier.
* **vCPU:** Virtual Central Processing Unit, the smallest unit of compute.

---

### 6. **AWS IAM (Identity and Access Management)**

IAM controls access to AWS services securely.

#### **Entities in IAM:**

* **Users:** Individuals with long-term credentials.
* **Groups:** Collection of users with common permissions.
* **Roles:** Temporary access granted to services or users.
* **Policies:** Permissions written in JSON format.

---

### 7. **AWS Cognito**

Amazon Cognito enables secure user sign-up, sign-in, and access control.

#### **Components:**

* **User Pools:** Managed user directories.
* **Federated Identities:** Allow users to authenticate via external identity providers (e.g., Facebook, Google).
* **Token-Based Authentication:** Supports OAuth and JWT tokens.

---

### 8. **Amazon S3 (Simple Storage Service)**

S3 is an object storage service used to store and retrieve any amount of data.

#### **Features:**

* **Storage Classes:** Standard, Intelligent-Tiering, Glacier, and more.
* **Key-Value Storage:** Objects are stored with a unique key.
* **Lifecycle Policies:** Automatically transition data between storage classes.
* **Versioning and Access Control:** Manage object versions and permissions.

---

### 9. **Amazon ECR (Elastic Container Registry)**



ECR is a fully managed Docker container registry that makes it easy to store, manage, and deploy container images.

#### **Key Integrations:**

* Works with **ECS (Elastic Container Service)** and **EKS (Elastic Kubernetes Service)** to deploy containerized applications.

* **CI/CD Pipelines:** Integrate ECR with AWS CodePipeline and CodeBuild for automated container image builds and deployments.
* **Third-Party Tools:** Use ECR with Jenkins, GitHub Actions, or GitLab CI for streamlined DevOps workflows.
* **Cross-Account Access:** Share container images across multiple AWS accounts using resource-based policies.
* **Image Scanning:** Integrate with Amazon Inspector or third-party security tools for vulnerability scanning of container images.


