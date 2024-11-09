

package org.OS;

import java.util.LinkedList;
import java.util.Queue;

public class Gate {

    private ParkingLot parkingLot;
    private String gatename;
    private Queue<Car> waitingQueue = new LinkedList<>(); // Queue to manage waiting cars

    public Gate(String gatename, ParkingLot parkingLot) {
        this.gatename = gatename;
        this.parkingLot = parkingLot;
    }

    public void in(Car car) throws InterruptedException {
        synchronized (parkingLot) {
            System.out.println("Car " + car.getCarId() + " from " + car.getGate() + " arrived at time " + car.getArrivalTime());

            boolean parkedImmediately = parkingLot.enterParking(car);

            if (parkedImmediately) {
                System.out.println("Car " + car.getCarId() + " from " + car.getGate() + " parked. (Parking Status: " + parkingLot.getCurrentCars() + " spots occupied)");
            } else {
                // Car has to wait for a spot
                waitingQueue.add(car);
                System.out.println("Car " + car.getCarId() + " from " + car.getGate() + " waiting for a spot.");

                // Wait until it's this car's turn
                while (waitingQueue.peek() != car || !parkingLot.enterParking(car)) {
                    parkingLot.wait();  // Wait for a spot and notifyAll()
                }

                waitingQueue.remove(); // Remove from queue once it parks
                System.out.println("Car " + car.getCarId() + " from " + car.getGate() + " parked after waiting. (Parking Status: " + parkingLot.getCurrentCars() + " spots occupied)");
            }
        }
    }

    public void out(Car car) {
        synchronized (parkingLot) {
            parkingLot.leaveParking();
            System.out.println("Car " + car.getCarId() + " from " + car.getGate() + " left after " + car.getParkingDuration() + " units of time parked. (Parking Status: " + parkingLot.getCurrentCars() + " spots occupied)");

            // Notify all waiting threads that a spot is now available
            parkingLot.notify();
        }
    }

    public String getGateName() {
        return gatename;
    }
}
// last update
