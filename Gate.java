package org.OS;

import java.util.LinkedList;
import java.util.Queue;

public class Gate {

    private ParkingLot parkingLot = new ParkingLot();
    private String gatename;
    private Queue<Car> waitingQueue = new LinkedList<>();  // Queue to manage waiting cars

    public Gate(String gatename, ParkingLot parkingLot) {
        this.gatename = gatename;
        this.parkingLot = parkingLot;
    }

    public void in(Car car) throws InterruptedException {
        synchronized (parkingLot) {
            System.out.println("Car " + car.getCarId() + " from " + car.getGate() + " arrived at time " + car.getArrivalTime());

            boolean parkedImmediately = parkingLot.enterParking();
            if (parkedImmediately) {
                // Car parked immediately
                System.out.println("Car " + car.getCarId() + " from " + car.getGate() + " parked. (Parking Status: " + parkingLot.getCurrentCars() + " spots occupied)");
            } else {
                // Car needs to wait for a spot
                waitingQueue.offer(car); // Add car to the waiting queue
                System.out.println("Car " + car.getCarId() + " from " + car.getGate() + " is waiting for a spot.");

                while (!parkingLot.enterParking()) {
                    parkingLot.wait(); // Wait for a parking spot to become available
                }
                // Once the car gets a spot
                waitingQueue.poll();  // Remove the car from the queue
                System.out.println("Car " + car.getCarId() + " from " + car.getGate() + " parked after waiting. (Parking Status: " + parkingLot.getCurrentCars() + " spots occupied)");
            }
        }
    }

    public void out(Car car) throws InterruptedException {
        synchronized (parkingLot) {
            parkingLot.leaveParking(); // Release the parking spot
            System.out.println("Car " + car.getCarId() + " from " + car.getGate() + " left after " + car.getParkingDuration() + " units of time parked. (Parking Status: " + parkingLot.getCurrentCars() + " spots occupied)");

            // Process the next car in the waiting queue if there's one
            if (!waitingQueue.isEmpty()) {
                Car nextCar = waitingQueue.peek();  // Get the car at the front of the queue
                synchronized (nextCar) {
                    nextCar.notify();  // Wake up the next car in line to park
                }
            }
            parkingLot.notifyAll(); // Notify all cars that a spot has been freed
        }
    }

    public String getGateName() {
        return gatename;
    }
}




