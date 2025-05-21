# Connect EC2 to RDS: MySQL & PostgreSQL

## Summary
This guide provides step-by-step instructions to connect an EC2 instance to RDS databases (MySQL and PostgreSQL) on AWS. It covers launching an EC2 instance, installing database clients, creating RDS databases, and performing basic SQL operations from the EC2 instance.

## 1. Launch EC2 Instance

### Step-by-Step: Launch an EC2 Instance

1. **Log in to AWS Console**
    - Go to [AWS Management Console](https://console.aws.amazon.com/).
    - Navigate to **EC2** under "Services".

2. **Launch Instance**
    - Click **Launch Instance**.
    - Enter a name (e.g., `ec2-rds-demo`).

3. **Choose Amazon Machine Image (AMI)**
    - Select **Ubuntu Server 22.04 LTS (HVM), SSD Volume Type**.

4. **Choose Instance Type**
    - Select **t2.micro** (Free Tier eligible).

5. **Configure Key Pair**
    - Under **Key pair (login)**, select an existing SSH key pair or create a new one.
    - Download and save the private key file (`.pem`) securely.

6. **Network Settings**
    - Click **Edit** under "Network settings".
    - Select the **VPC OF YOUR CHOICE**
    - Choose a **public subnet** (e.g., `subnet-xxxxxx`).
    - Ensure **Auto-assign public IP** is **Enabled**.
    - Under **Firewall (security groups)**:
      - Select **Create security group**.
      - Allow **SSH** (port 22) from your IP (or a trusted range).

7. **Storage**
    - Leave default storage settings (8 GiB gp3).

8. **Launch**
    - Review settings and click **Launch Instance**.

9. **Wait for Instance to Start**
    - Go to **Instances**.
    - Wait until the instance state is **running** and the status checks pass.

10. **Note Public IP**
     - Copy the **Public IPv4 address** for SSH access in the next steps.


## 2. Connect to EC2 & Install Clients

- SSH into your EC2 instance.
- Update and install MySQL and PostgreSQL clients:

```sh
sudo apt update -y
sudo apt install mysql-client postgresql-client -y
```

- Verify installation:
```sh
mysql --version
psql --version
```

## 3. Create RDS MySQL Database

Follow these steps to create a MySQL RDS database and enable connectivity from your EC2 instance:

1. **Open AWS Management Console**
    - Go to [AWS Management Console](https://console.aws.amazon.com/).
    - Navigate to **RDS** under "Services".

2. **Create Database**
    - Click **Create database**.
    - Choose **Standard Create**.
    - Under **Engine options**, select **MySQL**.
    - For **Edition**, choose the default (e.g., MySQL Community).
    - Under **Version**, select the latest available (or as required).

3. **Templates**
    - Select **Free tier** to stay within free usage limits.

4. **Settings**
    - Enter a **DB instance identifier** (e.g., `mysql-demo`).
    - Set a **Master username** (e.g., `admin`).
    - Enter and confirm a **Master password**.
    - Save these credentials securely.

5. **DB Instance Size**
    - Under **DB instance class**, select **db.t3.micro** (Free tier eligible).

6. **Storage**
    - Leave default storage settings (e.g., 20 GiB, General Purpose (SSD)).

7. **Connectivity**
    - Under **Virtual Private Cloud (VPC)**, select the **same VPC** as your EC2 instance.
    - For **Subnet group**, leave the default or select as needed.
    - **Public access**: Select **No** (recommended for security).
    - **VPC security group**: 
        - Choose **Create new** or **Choose existing**.
        - If creating new, allow inbound MySQL/Aurora (port 3306) from your EC2 instance's security group or IP.
    - **Availability zone**: Leave default.

8. **Additional Configuration**
    - Database name: Optionally specify a default database (e.g., `sampledb`).
    - Leave other settings as default unless customization is needed.

9. **Create Database**
    - Review all settings.
    - Click **Create database**.

10. **Wait for Database to Become Available**
    - Go to the **Databases** section in RDS.
    - Wait until the status is **Available**.

11. **Enable EC2 Connectivity**
    - Select your new database.
    - Click **Modify**.
    - Under **Connectivity**, ensure your EC2 instance's security group is allowed in the RDS security group (inbound rule for port 3306).
    - Optionally, use the "EC2 Connect" feature if available to add your EC2 instance.
    - Save changes and apply immediately if prompted.

12. **Note Connection Details**
    - In the database details page, copy the **Endpoint** (hostname), **Port** (default: 3306), **Master username**, and use the password you set earlier.
    - You will use these details to connect from your EC2 instance.

---
**Tip:**  
If you need to manually edit security groups, go to **EC2 > Security Groups**, select the RDS security group, and add an inbound rule:
- **Type:** MySQL/Aurora
- **Port:** 3306
- **Source:** Your EC2 instance's security group or its public IP (for testing only; not recommended for production).

## 4. Connect EC2 to MySQL RDS
- On your EC2 instance, connect to MySQL:

```sh
mysql -h <ENDPOINT> -u <USERNAME> -p
# Enter your password when prompted
```
- Create schema and table:
```sql
CREATE SCHEMA sampledb;
USE sampledb;
CREATE TABLE Products (
    ProductID INT PRIMARY KEY AUTO_INCREMENT,
    ProductName VARCHAR(100) NOT NULL,
    Category VARCHAR(50),
    Price DECIMAL(10,2),
    InStock BOOLEAN DEFAULT TRUE
);
```
- Insert sample data:
```sql
INSERT INTO Products (ProductName, Category, Price, InStock) VALUES
('Laptop', 'Electronics', 799.99, TRUE),
('Smartphone', 'Electronics', 499.99, TRUE),
('Desk Chair', 'Furniture', 89.99, TRUE),
('Notebook', 'Stationery', 2.49, TRUE),
('Coffee Mug', 'Kitchen', 7.99, FALSE);
```
- Query the table:
```sql
SELECT * FROM Products;
```

## 5. Create RDS PostgreSQL Database
- In AWS Console, create a **Free Tier PostgreSQL database** in the same VPC as your EC2 instance.
- Wait for the database to become available.
- Click **Modify** and add EC2 connectivity:
  - Add your EC2 instance under "EC2 Connect".
  - Follow on-screen instructions.
- Note the **Endpoint**, **Username**, and **Password** for your database.

## 6. Connect EC2 to PostgreSQL RDS
- On your EC2 instance, connect to PostgreSQL:

```sh
psql -h <ENDPOINT> -U <USERNAME> -W
# Enter your password when prompted
```
- Create schema and table:
```sql
CREATE SCHEMA sampledb;
CREATE TABLE sampledb.products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
- Insert sample data:
```sql
INSERT INTO sampledb.products (name, description, price) VALUES
('Laptop', 'High performance laptop', 1200.00),
('Smartphone', 'Latest model smartphone', 800.00),
('Headphones', 'Noise cancelling headphones', 150.00),
('Monitor', '27-inch 4K monitor', 350.00),
('Keyboard', 'Mechanical keyboard', 90.00);
```
- Query the table:
```sql
SELECT * FROM sampledb.products;
```

---
**Note:**
- Ensure your RDS security group allows inbound connections from your EC2 instance's security group or public IP.
- Replace `<ENDPOINT>`, `<USERNAME>`, and `<PASSWORD>` with your actual database details.
- For production, never expose RDS to the public internet.