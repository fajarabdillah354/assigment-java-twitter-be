# Twitter Backend (Spring Boot + OracleSQL)

## 📌 Overview
Backend untuk aplikasi Twitter menggunakan **Spring Boot** dan **OracleSQL**, dengan fitur:
✅ **Autentikasi JWT**  
✅ **Follow/Unfollow User**  
✅ **Postingan & Feed**  
✅ **Pencarian User**  
✅ **Dokumentasi API dengan Swagger**

---

## 📂 Project Structure
```
📂 src/main/java/dev/codejar/
 ┣ 📂 config/               # Konfigurasi aplikasi (JWT, Security, dll.)
 ┣ 📂 controller/           # REST API Controllers
 ┣ 📂 entity/               # Database Entities
 ┣ 📂 exception/            # Custom Exception Handlers
 ┣ 📂 payload/              # DTOs (Request & Response)
 ┣ 📂 repository/           # Database Repositories
 ┣ 📂 service/              # Business Logic Services
 ┣ 📂 swagger/              # Swagger API Configuration
 ┣ 🏗 Application.java      # Main entry point
```

---

## 📦 Dependencies (Maven POM.xml)
- **Spring Boot** (Web, Security, Data JPA)
- **OracleSQL JDBC Driver**
- **JWT Authentication**
- **Lombok**
- **Swagger API Documentation**

---

## 🔗 API Endpoints Overview
### 🔑 Authentication (Login & Register)
- `POST /auth/register` → Register user baru.
- `POST /auth/login` → Login user & generate JWT.

### 👥 Follow System
- `POST /follow/{userId}` → Mengikuti pengguna lain.
- `DELETE /unfollow/{userId}` → Berhenti mengikuti pengguna lain.
- `GET /followers/{userId}` → Daftar followers.
- `GET /followees/{userId}` → Daftar followees.

### 📝 Post Management
- `POST /posts` → Membuat postingan baru.
- `GET /posts` → Feed dari pengguna yang diikuti.

### 🔎 User Search
- `GET /users/search?name=xxx` → Cari user berdasarkan nama.

---

## 🚀 How to Run the Project
### 1️⃣ Setup Database (OracleSQL)
Edit **`application.properties`** agar sesuai dengan database lokal:
```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.hibernate.ddl-auto=create
```

### 2️⃣ Build & Run
```sh
mvn clean install
mvn spring-boot:run
```

### 3️⃣ Akses API Docs
Swagger UI dapat diakses di:
👉 `http://localhost:8080/swagger-ui.html`

---

## 📌 Conclusion
Proyek ini adalah **backend layanan Twitter** berbasis **Spring Boot + OracleSQL**.
Dapat dikembangkan lebih lanjut untuk menambahkan fitur tambahan! 🚀

