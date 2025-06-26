# 🔢 Spring Boot + Redis HyperLogLog Demo

This project demonstrates how to use **Redis HyperLogLog** in a Spring Boot application to estimate the number of **unique visitors** with high efficiency and minimal memory usage.

---

## 🚀 Features

- Track daily unique users with approximate counts
- Compare **HyperLogLog estimate** vs **exact count**
- Simulate large traffic loads (10k–90k+ visits)
- Measure and log **memory usage**, **accuracy**, and **performance**
- Minimal dependencies, clean architecture

---

## 🛠 Tech Stack

| Component     | Description                    |
|---------------|--------------------------------|
| Spring Boot   | Backend framework              |
| Spring Data Redis | For HyperLogLog & Redis ops |
| Redis         | HyperLogLog support (~12KB)    |
| Java 17+      | Recommended                    |
| curl / Postman| For testing APIs               |

---

## 📦 Setup Instructions

### 1. Prerequisites

- Java 17+
- Redis Server running on `localhost:6379`

### 2. Run the App

```bash
./mvnw spring-boot:run

🧪 Testing the App
✅ Simulate Traffic
curl -X POST http://localhost:8080/api/simulate/10000
curl -X POST http://localhost:8080/api/simulate/50000
curl -X POST http://localhost:8080/api/simulate/90000
This simulates N unique user visits (e.g. user_1, user_2, ...) and logs:

✅ HyperLogLog estimate

✅ Exact count (HashSet)

✅ Estimation error (%)

✅ Memory usage of each approach

---
📉 Sample Output
✅ Simulated 90000 visits in 417 ms.
🧠 Memory used during simulation: ~5124 KB

========== Visitor Stats ==========
🔢 HyperLogLog Estimate : 89780
🎯 Exact Unique Count    : 90000
📉 Estimation Error      : 0.24%
📦 HLL Memory (fixed)    : ~12 KB
💾 Exact Set Memory Used : ~5636 KB
==================================
---
🧑‍💻 Author
Made by Tanmay Mone
