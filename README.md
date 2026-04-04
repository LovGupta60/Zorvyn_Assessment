#  Finance Data Processing & Access Control Backend

---

##  Project Title

**Finance Dashboard Backend System**

---

##  Description

This project is a backend application built using Spring Boot to manage financial data and provide role-based access control for a finance dashboard system. It allows users to create, view, update, and analyze financial records such as income and expenses while enforcing strict permissions based on user roles (Admin, Analyst, Viewer).

The system is designed to demonstrate backend engineering concepts such as REST API design, layered architecture, JWT-based authentication, role-based authorization, and data aggregation for dashboards. It also includes features like filtering, searching, soft delete, and summary analytics, making it suitable for real-world financial tracking applications.

---

##  Getting Started

###  Dependencies

Before running the project, ensure you have:

* Java 17 or higher
* Maven
* MySQL / PostgreSQL (or any relational DB)
* Postman (for API testing)
* OS: Windows 10 / Linux / macOS

---

##  Installing

### Step 1: Clone the Repository

```bash
git clone https://github.com/your-username/Zorvyn_Project.git
```

### Step 2: Navigate to Project Directory

```bash
cd Zorvyn_Project
```

### Step 3: Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/finance_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

##  Executing Program

### Step 1: Build the Project

```bash
mvn clean install
```

### Step 2: Run the Application

```bash
mvn spring-boot:run
```

### Step 3: Access APIs

```text
http://localhost:8080
```

---

##  Authentication Usage

1. Register or login using:

   ```
   POST /auth/signup
   POST /auth/login
   ```
2. Copy JWT token
3. Add in header:

   ```
   Authorization: Bearer <token>
   ```

---

##  Core APIs Overview

###  Auth APIs

* Signup, Login
* Get current user (`/auth/me`)
* Update role (Admin only)
* Toggle user status

###  Financial APIs

* Create, Read, Update, Delete records
* Filter by type, category, date
* Search by keyword

###  Dashboard APIs

* Total income & expenses
* Net balance
* Category-wise totals
* Monthly trends
* Recent transactions

---

##  Help

### Common Issues & Fixes

❌ **JWT not working**
✔ Ensure token is added in Authorization header

❌ **Empty search result**
✔ Check case sensitivity or keyword match

❌ **User not found**
✔ Ensure username + role are correct

❌ **403 Forbidden**
✔ Check user role permissions

---

##  Authors

* **Lov Gupta**

  * GitHub: @LovGupta60

---

##  Version History

### 🔹 0.2

* Added search & filter functionality
* Implemented soft delete + permanent delete
* Added role update & toggle status

### 🔹 0.1

* Initial release
* Basic CRUD + authentication

---

##  License

This project is licensed under the **MIT License** - see the LICENSE.md file for details.

---

##  Acknowledgments

* Inspired by real-world finance tracking systems
* Spring Boot documentation
* JWT authentication tutorials
* Open-source community resources

---

##  Additional Notes

* Designed with **clean architecture (Controller → Service → Repository)**
* Uses **DTO pattern** for secure API responses
* Implements **role-based access control using Spring Security**
* Soft delete ensures data safety and audit capability

---

##  Conclusion

This project demonstrates strong backend fundamentals including API design, security, and data handling. It is structured to be scalable, maintainable, and easily extendable for future enhancements like frontend integration or deployment.

---
