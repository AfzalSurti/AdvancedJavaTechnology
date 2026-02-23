# 📡 Java RMI Example — Read File From Another Machine

## 🎯 Aim

Create a Java RMI program where a **Client reads a file located on a Server machine** using Remote Method Invocation.

---

## 🧠 What is RMI?

**RMI (Remote Method Invocation)** allows one Java program to call a method of another Java program running on a different machine **as if it were a local call**.

👉 Client does NOT access the file directly.
👉 Server reads the file and sends back the content.

---

## 🏗️ Project Structure

```
question_1/
│
├── FileService.java        (Remote Interface)
├── FileServiceImpl.java    (Implementation)
├── FileServer.java         (Server)
├── FileClient.java         (Client)
└── test.txt                (File on Server)
```

---

## 📁 1️⃣ FileService.java — Remote Interface

Defines what method client can call.

```java
public interface FileService extends Remote {
    String readFile(String fileName) throws RemoteException;
}
```

✔ Acts like a **contract** between client and server.

---

## 📁 2️⃣ FileServiceImpl.java — Implementation

Actual logic to read the file from server disk.

✔ Opens file
✔ Reads content
✔ Returns text to client

---

## 📁 3️⃣ FileServer.java — Starts the Server

```java
LocateRegistry.createRegistry(1099);
registry.rebind("FileService", obj);
```

✔ Starts RMI Registry (Port 1099)
✔ Registers service name `"FileService"`
✔ Waits for client requests

---

## 📁 4️⃣ FileClient.java — Calls Remote Method

```java
Registry registry = LocateRegistry.getRegistry("localhost",1099);
FileService service = (FileService) registry.lookup("FileService");

String data = service.readFile("test.txt");
System.out.println(data);
```

✔ Connects to server
✔ Calls `readFile()` remotely
✔ Prints file content

---

## ▶️ How To Run The Program

### Step 1 — Compile All Files

```
javac *.java
```

---

### Step 2 — Start Server FIRST

```
java FileServer
```

Output:

```
Server is ready...
```

---

### Step 3 — Run Client

(Open another terminal)

```
java FileClient
```

Output:

```
Hello Students
Welcome to RMI
```

---

## 🔄 How RMI Works Internally

```
Client → asks Registry for FileService
Registry → gives remote reference
Client → calls readFile()
Network → sends request to Server
Server → reads file → sends data back
Client → prints result
```

---

## 📌 Important Notes

✔ Default RMI Port = **1099**
✔ Only ONE registry can run on this port
✔ File must exist on SERVER machine
✔ Both machines must be on same network (if remote)

---

## ❗ If You Get Error: "Port 1099 Already In Use"

Kill existing process:

```
netstat -ano | findstr :1099
taskkill /PID <PID> /F
```

---

## 🧾 One-Line Definition (For Exam)

> Java RMI enables a program to invoke methods of an object running on another JVM, enabling distributed computing.

---

## 🎯 Real-Life Use Cases

✔ Distributed Banking Systems
✔ Remote File Access
✔ Enterprise Java Applications
✔ Early Microservices Architecture

---

## ✅ Key Concept To Remember

```
Interface → What can be called
Implementation → How work is done
Server → Publishes service
Client → Uses service remotely
Registry → Connects them
```

---

**This is NOT file sharing — it is Remote Method Calling.**
Client never touches the file directly.
Server does the work and returns the result.

---


