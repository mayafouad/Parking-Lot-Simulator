package OS_A2;

class Car extends Thread {
    private Gate gate;
    private int carId;
    private int arrivalTime;
    private int parkingDuration;

    public Car(Gate gate, int carId, int arrivalTime, int parkingDuration) {
        this.gate = gate;
        this.carId = carId;
        this.arrivalTime = arrivalTime;
        this.parkingDuration = parkingDuration;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(arrivalTime * 1000);
            gate.in(this); // This will block if no spots are available
            Thread.sleep(parkingDuration * 1000);
            gate.out(this);
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        } 
    }

    public String getGate() {
        return gate.getGateName();
    }
    public int getCarId() {
        return carId;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
    public int getParkingDuration() {
        return parkingDuration;
    }

}