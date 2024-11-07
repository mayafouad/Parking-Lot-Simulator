# OS_A2
This division works well for simplifying tasks but has some overlapping elements that might make integration challenging. Here’s a refined approach for each of these roles to make the responsibilities clearer and to ensure minimal overlap:

---

### Sara: Parking Lot Setup and Management
   - *Setup Parking Lot*: Create a ParkingLot class with a limit of 4 parking spots. 
   - *Parking Spot Release*: Implement functionality to release a parking spot once a car leaves.
   - *Parking Status Tracking*: Keep track of the number of cars in the lot and provide this information to other classes as needed.
   - *Documentation*: Ensure detailed documentation on how the ParkingLot works so that the team can understand how cars interact with it.

### Aya: Gate Simulation
   - *Implement Gates*: Create three gate threads (Gate 1, Gate 2, Gate 3) in the simulation. Each gate should handle car arrivals independently.
   - *Input Parsing and Data Distribution*: Read and parse the input file to identify arrival data for each gate, then pass this data to the appropriate gate thread.
   - *Gate Logging*: Log the arrival of each car at each gate, along with any initial waiting status if the lot is full.
   - *Coordination with Car Threads*: Work with Team Member 3 to ensure that each gate correctly assigns cars for handling.

### Maya: Car Threads and Synchronization
   - *Car Thread Creation*: Implement the Car class where each car is represented by a thread. The Car class should handle parking attempts, waiting (if no spot is available), parking duration, and leaving.
   - *Arrival and Parking Simulation*: Use sleep() to simulate arrival and parking times as specified by each car’s data. Ensure each car releases its spot upon departure.
   - *Car-Related Logging*: Log each action of a car—arrival, parking, waiting (if parking is full), and departure.
   - *Synchronization*: Coordinate with Team Member 1 to ensure cars park and exit synchronously without exceeding the 4-car limit.

### Menna: Semaphore and Reporting
   - *Semaphore Management*: Implement semaphores to control parking spot availability, ensuring no more than 4 cars are parked at a time.
   - *Real-Time Reporting*: Track the number of cars currently in the parking lot and update this status after every parking and departure event.
   - *Final Report Generation*: After the simulation, print the total number of cars served and breakdown of cars served per gate.
   - *Concurrency Testing*: Conduct final integration and concurrency testing to ensure that all components work together without race conditions, deadlocks, or other synchronization issues.

---

### Final Integration Workflow:
   - *Initial Setup and API Agreements*: Ensure each member’s class has a defined API (methods and data structures) to interact with other components.
   - *Regular Sync Meetings*: Schedule quick check-ins to align on integration progress and resolve any interdependencies.
   - *Final Integration and Testing*: Once each component is complete, integrate and test to ensure smooth coordination between parking lot setup, gate simulation, car threads, and semaphore management.
