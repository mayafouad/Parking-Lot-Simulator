package org.OS;


public class ParkingLot {
    private final semaphore spots;
    private int currentCars = 0;
    private int totalCarsServed = 0;

    public ParkingLot() {
        spots = new semaphore(4, true); // Fair semaphore for FIFO order
    }

    public boolean enterParking(Car car) {
        try {
            // Attempt to acquire a parking spot
            if (spots.tryAcquire()) {
                synchronized (this) {
                    currentCars++;
                    totalCarsServed++;
                }
                return true;
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        return false;
    }

    public void leaveParking() {
        spots.release(); // Release the permit back to the semaphore
        synchronized (this) {
            currentCars--;
        }
    }

    public synchronized int getCurrentCars() {
        return currentCars;
    }

    public synchronized int getTotalCarsServed() {
        return totalCarsServed;
    }
}
// last update 
