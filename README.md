# Inventory Management System

A web application for managing products, suppliers, customers, purchases, sales, returns, and stock levels. It supports basic inventory workflows, searchable transaction records, and low-stock indicators.

The application interface is in Chinese. This README provides an English overview and setup instructions.

## Main Features

- **Product management:** Create, edit, delete, and search products; view stock quantities.
- **Supplier and customer management:** Maintain and search business contact records.
- **Purchases:** Record purchases, update purchase quantities, and filter records by supplier, product, and date.
- **Sales:** Record sales, update sales quantities, and filter records by customer, product, and date.
- **Returns:** Record purchase returns and sales returns with corresponding stock adjustments.
- **Low-stock indicators:** Display products whose stock is below their configured threshold.
- **Administration:** Manage users, departments, roles, menus, permission assignments, announcements, and login records.
- **Supporting features:** Profile updates, password changes, image uploads, and in-memory cache management.

## Technology Stack

| Layer | Technologies |
| --- | --- |
| Backend | Java 8, Spring Boot 2.2.1.RELEASE, Spring MVC |
| Frontend | HTML, CSS, JavaScript, Layui 2.5.5, jQuery |
| Templates | Thymeleaf |
| Data access | MyBatis-Plus 3.2.0, Druid 1.1.20 |
| Authentication and permissions | Apache Shiro 1.4.0 |
| Database | MySQL 5.7 |
| Build and API documentation | Maven, Swagger 2 |

Spring Boot renders the Thymeleaf pages, and the browser uses jQuery to call the application endpoints. Backend code is organized into controllers, services, mappers, and entities.

Purchases and sales returns increase stock. Sales and purchase returns decrease stock. Editing purchase or sales quantities applies the difference to the current stock. The purchase service uses a Spring transaction for the stock update and purchase record save.

## Run Locally

### Requirements

- JDK 8 with `JAVA_HOME` configured
- MySQL 5.7
- Maven, or the included Maven Wrapper

The commands below use PowerShell and should be run from the project root.

### 1. Initialize the database

Open a MySQL client:

```text
mysql -u root -p
```

Import the scripts in order:

```sql
SOURCE database/schema.sql;
SOURCE database/seed-demo.sql;
```

You can also import them with a database client such as Navicat. The scripts create a development database named `warhouse` with 15 tables. Use a new development database, not an existing business database.

The demo seed contains menus, permissions, roles, and a demo administrator. Business tables start empty; suppliers, customers, and products can be added through the application.

### 2. Configure the application

Copy the example files and set your own database credentials. If local configuration files already exist, review them before replacing them.

```powershell
Copy-Item src/main/resources/application.example.yml src/main/resources/application.yml
Copy-Item src/main/resources/file.example.properties src/main/resources/file.properties
$env:DB_USERNAME = 'root'
$env:DB_PASSWORD = 'YOUR_DATABASE_PASSWORD'
```

| Environment variable | Purpose | Default |
| --- | --- | --- |
| `DB_URL` | MySQL JDBC connection URL | Local `warhouse` database on port `3306` |
| `DB_USERNAME` | Database username | `root` |
| `DB_PASSWORD` | Database password | Empty; set your own value |
| `PORT` | HTTP port | `8888` |

Set the image upload directory using `filepath` in `file.properties`. The example uses `./uploads/`, relative to the working directory.

### 3. Start the application

```powershell
.\mvnw.cmd spring-boot:run
```

If Maven is installed, you can use `mvn spring-boot:run`. The first run requires an internet connection to download dependencies.

Open [http://localhost:8888/index.html](http://localhost:8888/index.html).

Demo credentials, created by `database/seed-demo.sql`:

- Username: `admin`
- Password: `ChangeMe123!`
- Enter the verification code shown on the login page.

Change the demo password after signing in.

### 4. Build

```powershell
.\mvnw.cmd -DskipTests package
```

The output is `target/warehouse-0.0.1-SNAPSHOT.jar`. This command skips tests. Running the existing Spring Boot tests requires a reachable database and local configuration.

## Project Structure

```text
database/                          Schema and demo seed data
src/main/java/com/yeqifu/bus/       Products, purchases, sales, and returns
src/main/java/com/yeqifu/sys/       Users, permissions, and administration
src/main/resources/templates/     Thymeleaf pages
src/main/resources/static/        JavaScript, CSS, images, and frontend libraries
src/main/resources/mapper/        MyBatis XML files
src/test/                         Basic tests and development utilities
```

## Project Status

This is a learning and demonstration project. Areas for improvement include server-side validation, operation-level API authorization, concurrent stock updates, transaction handling in sales and returns, and cache consistency. Customer deletion currently has an HTTP method mismatch between the page and the backend.

## Data and Attribution

Local configuration files, database passwords, the original database dump, uploaded files, and build output are excluded from Git. The initialization scripts do not include the original customer, contact, or user records.

Existing author notices and third-party licenses are retained. See the bundled frontend template [license](src/main/resources/static/resources/LICENSE).
