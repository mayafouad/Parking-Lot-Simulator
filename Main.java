package org.os;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Create a parking lot with 4 spots (you can adjust the number of spots)
        ParkingLot parkingLot = new ParkingLot();  // Initialize parking lot with 4 spots

        // Create Gate instances and pass the parking lot to each gate
        Gate gate1 = new Gate("Gate 1", parkingLot);
        Gate gate2 = new Gate("Gate 2", parkingLot);
        Gate gate3 = new Gate("Gate 3", parkingLot);



        List<Car> cars = new ArrayList<>();
        int countergates1=0,countergates2=0,countergates3=0;

        // Read car data from file
        try (BufferedReader br = new BufferedReader(new FileReader("cars.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Example line: Gate 1, Car 0, Arrive 0, Parks 3
                String[] details = line.split(", ");

                String gateName = details[0];
                int carId = Integer.parseInt(details[1].split(" ")[1]);
                int arrivalTime = Integer.parseInt(details[2].split(" ")[1]);
                int parkDuration = Integer.parseInt(details[3].split(" ")[1]);

                // Select the correct gate
                Gate gate;

                if (gateName.equals("Gate 1")) {
                    gate = gate1;
                    countergates1++;

                } else if (gateName.equals("Gate 2")) {
                    gate = gate2;
                    countergates2++;
                } else if (gateName.equals("Gate 3")) {
                    gate = gate3;
                    countergates3++;
                } else {
                    continue; // Skip invalid gate names
                }

                // Create Car instance and add to the list
                Car car = new Car(gate, carId, arrivalTime, parkDuration);
                cars.add(car);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Start each car thread
        for (Car car : cars) {
            car.start(); // This will call the run() method of the Car thread
        }

        // Wait for all cars to finish parking before the main thread ends
        for (Car car : cars) {
            try {
                car.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Optional: Display final report
        System.out.println("..."+"\n"+"Total cars served: " + parkingLot.getTotalCarsServed());
        System.out.println(" Current Cars in Parking: "+ parkingLot.getCurrentCars());
        System.out.println("Details: ");
        System.out.println("- Gate 1 served  "+countergates1+" cars" );
        System.out.println("- Gate 2 served  "+countergates2+" cars" );
        System.out.println("- Gate 3 served  "+countergates3+" cars" );


        // Optionally, you can call reports or other methods here if needed
    }
}
