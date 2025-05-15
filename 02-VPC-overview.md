# AWS VPC Overview

Amazon Virtual Private Cloud (VPC) allows you to launch AWS resources in a logically isolated virtual network that you define. It provides complete control over your virtual networking environment, including resource placement, connectivity, and security.

## Key Components of AWS VPC

1. **Subnets**: Subdivisions of a VPC that allow you to group resources based on security and operational needs. Subnets can be public or private.
2. **Route Tables**: Define how traffic is routed within the VPC and to external networks.
3. **Internet Gateway (IGW)**: Enables communication between resources in your VPC and the internet.
4. **NAT Gateway**: Allows private subnet resources to access the internet without exposing them to inbound traffic.
5. **Elastic IP (EIP)**: A static, public IPv4 address that you can associate with resources in your VPC.
6. **Security Groups**: Virtual firewalls that control inbound and outbound traffic for resources.
7. **Network ACLs (Access Control Lists)**: Optional layer of security for subnets that acts as a stateless firewall.
8. **VPC Peering**: Connects two VPCs for resource sharing.
9. **Endpoints**: Enables private connectivity to AWS services without using the internet.
10. **VPN Gateway**: Connects your on-premises network to your VPC.
11. **VPC Flow Logs**: Captures information about the IP traffic going to and from network interfaces in your VPC.

---

# Walkthrough: Create a VPC Using the AWS Management Console

1. **Log in to the AWS Management Console**:
    - Navigate to the [VPC Dashboard](https://console.aws.amazon.com/vpc/).

    2. **Choose "VPC and more"**:
        - On the VPC Dashboard, click **Create VPC** and select the **VPC and more** option.

    3. **Configure VPC Settings**:
        - Enter a name for your VPC (e.g., `MyCustomVPC`).
        - Specify the IPv4 CIDR block (e.g., `10.0.0.0/16`).
        - Leave IPv6 CIDR block as **No IPv6 CIDR block**.

    4. **Configure Subnets**:
        - Under **Subnets**, specify the number of public subnets as `3` and private subnets as `3`.
        - Ensure the subnets are distributed across different Availability Zones for high availability.

    5. **Disable NAT Gateway and S3 Gateway**:
        - Under **NAT gateways**, select **None**.
        - Under **S3 gateway endpoint**, uncheck the option to create an S3 Gateway.

    6. **Review and Create**:
        - Review the configuration summary.
        - Click **Create VPC** to provision the VPC with the specified settings.

---

This completes the VPC creation process.