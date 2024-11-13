package org.os;

public class semaphore {
    private int value;
    private final boolean fair;

    public semaphore(int initial, boolean fair) {
        this.value = initial;
        this.fair = fair;
    }

    public synchronized boolean tryAcquire() {
        if (value > 0) {
            value--;
            return true;
        }
        return false;
    }

    public synchronized void release() {
        value++;
        notify();
    }
}
