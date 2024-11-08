package org.OS;

import java.util.concurrent.Semaphore;

public class ParkingLot {
    private final Semaphore spots;
    private int currentCars = 0;
    private int totalCarsServed = 0;

    public ParkingLot() {
        spots = new Semaphore(4);
    }

    
    public boolean enterParking() throws InterruptedException {
        if (spots.tryAcquire()) {
            synchronized (this) {
                currentCars++;
                totalCarsServed++;
            }
            return true;
        }
        return false;
    }


    public void leaveParking() {
        spots.release();
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
