# Console-Based Parking Lot Simulator 🚗
Simulate a multithreaded parking system using Java, leveraging threads and semaphores for efficient synchronization and concurrency control.🚧

### 🚀 Key Features
Thread Synchronization: Seamlessly manage shared parking spots across 3 independent gates.
Real-Time Reporting: Generate detailed logs of parking activity, including car arrivals, parking status, and total cars served.
Concurrency Control: Ensure safe and orderly access to resources using semaphores to avoid race conditions.

### 🛠️ System Overview
Parking Capacity: 4 parking spots shared dynamically among all incoming cars.
Gate Operations: 3 gates operate concurrently, managing car arrivals independently for a realistic simulation.
Input Format: Car schedules are loaded from a text file for streamlined processing.
Output Details: Logs display real-time updates on the parking process, including arrivals, parking status, and departures.

### ⚙️ Technologies Used
Java: Core implementation language for the simulator.
Threads: Simulate independent gate operations and car movements.
Semaphores: Synchronize and manage access to shared parking resources efficiently.
This project demonstrates the effective use of multithreading, synchronization techniques, and real-time logging in Java, showcasing a practical approach to concurrency management.
