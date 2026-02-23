# 📚 Java RMI + SQLite Example — Fetch Book Record from Remote Database

## 🎯 Aim

Create an **RMI-based Java application** where a **Client requests a book record** from a **remote Server**, and the Server fetches the data from an **SQLite database** and returns it as a `Book` object.

---

## 🧠 Concept

**RMI (Remote Method Invocation)** allows a client to call a method on a server **as if it were a local function**, while Java handles:

* Network communication
* Object serialization
* Data transfer

👉 Client does NOT access the database directly.
👉 Server queries SQLite and sends the result back.

---

## 🏗️ Project Structure

```
question_2/
│
├── Book.java                → Serializable model class
├── BookService.java         → Remote Interface (contract)
├── BookServiceImpl.java     → Server logic + SQLite queries
├── BookServer.java          → Starts RMI server
├── BookClient.java          → Sends request and displays result
├── books.db                 → SQLite database (auto-created)
└── sqlite-jdbc-3.51.1.0.jar → JDBC driver
```

---

## 📁 File Responsibilities

### 1️⃣ `Book.java`

Represents a book record and must implement `Serializable` so RMI can send it over the network.

Contains:

* id
* title
* author
* price

---

### 2️⃣ `BookService.java` (Remote Interface)

Defines methods client can call remotely:

```java
Book getBookById(int id);
Book getBookByTitle(String title);
```

Acts like a **contract between client and server**.

---

### 3️⃣ `BookServiceImpl.java`

Implements the interface and performs:

* SQLite connection (`jdbc:sqlite:books.db`)
* Table creation (if not exists)
* Inserts sample data (first run only)
* Executes SQL queries:

  ```sql
  SELECT * FROM books WHERE id = ?
  ```

---

### 4️⃣ `BookServer.java`

Starts RMI registry and publishes service:

```java
LocateRegistry.createRegistry(1099);
registry.rebind("BookService", service);
```

Server now waits for client requests.

---

### 5️⃣ `BookClient.java`

Connects to server and calls remote function:

```java
Book b = service.getBookById(2);
System.out.println(b);
```

---

## 🔄 Execution Flow

```
Client → Calls getBookById()
       ↓
RMI Registry finds BookService
       ↓
Server executes SQL query on SQLite
       ↓
Book object created
       ↓
Object serialized → sent over network
       ↓
Client receives → prints result
```

---

## ▶️ How To Compile & Run (Windows PowerShell)

### Step 1 — Go to Project Folder

```powershell
cd D:\AdvancedJavaTechnology\Assignment-4\question_2
```

---

### Step 2 — Compile (include SQLite JDBC driver)

```powershell
javac -cp ".;sqlite-jdbc-3.51.1.0.jar" *.java
```

---

### Step 3 — Run Server

```powershell
java -cp ".;sqlite-jdbc-3.51.1.0.jar" BookServer
```

Expected Output:

```
RMI Registry started...
Sample books inserted into SQLite DB
BookServer is ready.
```

---

### Step 4 — Run Client (new terminal)

```powershell
java -cp ".;sqlite-jdbc-3.51.1.0.jar" BookClient
```

Output:

```
Book Found ✅
ID: 2
Title: Effective Java
Author: Joshua Bloch
Price: 650
```

---

## 🌐 Running on Different Machines

In `BookClient.java`, change:

```java
LocateRegistry.getRegistry("localhost",1099);
```

to:

```java
LocateRegistry.getRegistry("SERVER_IP",1099);
```

Ensure:

* Same network
* Port **1099** allowed in firewall

---

## ❗ Common Errors & Fixes

| Error                                   | Cause                    | Fix                                       |
| --------------------------------------- | ------------------------ | ----------------------------------------- |
| Port 1099 already in use                | Old registry running     | Kill process using `netstat` + `taskkill` |
| ClassNotFoundException: org.sqlite.JDBC | JAR missing in classpath | Add jar in `-cp`                          |
| Book not found                          | Wrong ID/title           | Check DB values                           |
| Invalid filename *.java                 | Wrong folder             | `cd question_2` first                     |

---

## 🧾 One-Line Exam Definition

> This program uses Java RMI to allow a client to invoke a remote method that queries an SQLite database and returns a serialized Book object over the network.

---

## 🎯 Key Learning Outcome

✔ Understand distributed object communication
✔ Learn how RMI abstracts socket programming
✔ Integrate RMI with a real database (SQLite)
✔ Transfer complex objects across JVMs

---

## 🔑 Remember This Architecture

```
Client → Remote Interface → RMI → Server Implementation → SQLite DB
```

This is the **core idea of distributed systems in Java**.
