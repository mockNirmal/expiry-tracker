# Expiry Tracker API

A **Spring Boot REST API** for tracking expiry dates of food and medicine batches in a store.  
The system allows you to register product batches (barcode, SKU, expiry date, quantity) and automatically generates reminders **before expiry** and **on the expiry date**.

This project is designed as a **portfolio-ready backend application**, demonstrating clean architecture, database usage, scheduling, validation, and API documentation.

---

## 🚀 Features

- Add and manage product batches (barcode, SKU, name, expiry date, quantity)
- Track multiple batches for the same SKU (real-world inventory model)
- Automatically detect:
  - Items expiring soon (configurable, default 7 days)
  - Items expiring today
- Scheduled background job for expiry checks
- Notification audit log (no duplicate reminders)
- RESTful API with validation
- Interactive API documentation using Swagger/OpenAPI

---

## 🧱 Tech Stack

- **Java**: 24
- **Framework**: Spring Boot
- **Build Tool**: Maven
- **Database**: H2 (in-memory / file-based)
- **ORM**: Spring Data JPA (Hibernate)
- **Scheduling**: Spring `@Scheduled`
- **API Docs**: springdoc-openapi (Swagger UI)

---

## 📂 Project Structure

```
expiry-tracker/
 ├── pom.xml
 ├── src/main/java/com/yourname/expirytracker/
 │   ├── ExpiryTrackerApplication.java
 │   ├── items/
 │   │   ├── Batch.java
 │   │   ├── BatchRepository.java
 │   │   ├── BatchService.java
 │   │   ├── BatchController.java
 │   ├── notifications/
 │   │   ├── NotificationLog.java
 │   │   ├── NotificationType.java
 │   │   ├── NotificationLogRepository.java
 │   │   ├── NotificationService.java
 │   │   ├── ExpiryScheduler.java
 │   └── config/
 │       └── OpenApiConfig.java
 └── src/main/resources/
     └── application.properties
```

---

## ▶️ Running the Application

### Prerequisites
- Java 24 installed
- Maven 3.6+

### Start the app

```bash
mvn spring-boot:run
```

The application will start on:
```
http://localhost:8080
```

---

## 🗄️ Database (H2 Console)

H2 console is enabled for development.

Open in browser:
```
http://localhost:8080/h2-console
```

Login details:
- **JDBC URL**: `jdbc:h2:mem:expirydb`
- **Username**: `sa`
- **Password**: *(empty)*

---

## 📘 API Documentation (Swagger)

Interactive Swagger UI is available at:
```
http://localhost:8080/swagger-ui/index.html
```

You can:
- View all endpoints
- See request/response models
- Execute API calls directly from the browser

---

## 🔌 API Endpoints (Summary)

### Batches
- `POST /api/batches` – Create a new product batch
- `GET /api/batches` – List all batches
- `GET /api/batches/expiring?days=7` – Batches expiring within N days
- `GET /api/batches/expiring-today` – Batches expiring today

### Notifications
- `GET /api/notifications` – View notification audit log

---

## 🧪 Example Request

Create a batch:

```bash
curl -X POST http://localhost:8080/api/batches \
  -H "Content-Type: application/json" \
  -d '{
    "barcode":"1234567890123",
    "sku":"SKU-001",
    "name":"Milk 1L",
    "expiryDate":"2026-01-10",
    "quantity":12
  }'
```

---

## ⏰ Expiry Reminder Logic

A scheduled job runs daily:
- Sends a reminder **N days before expiry** (default: 7)
- Sends a reminder **on the expiry date**
- All reminders are stored in `NOTIFICATION_LOG`
- Duplicate reminders are prevented using a unique constraint

The reminder window is configurable in `application.properties`:
```properties
expiry.reminder.days=7
```

---

## 🎯 Why this project matters

This project demonstrates:
- Clean REST API design
- Real-world inventory modeling (batch-based expiry tracking)
- Database integrity & validation
- Background job scheduling
- Configuration-driven business rules
- API documentation & developer experience

It is suitable as a **backend portfolio project** for junior to mid-level Java backend roles.

---

## 🔮 Future Enhancements

- Email notifications (SMTP / SendGrid)
- PostgreSQL integration
- Authentication & roles
- Frontend dashboard (React)
- Barcode lookup via external APIs
- Dockerized deployment

---

## 👤 Author

**Your Name**  
GitHub: https://github.com/mockNirmal

---

## 📄 License

MIT License

