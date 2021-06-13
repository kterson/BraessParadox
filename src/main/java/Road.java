import java.util.Date;

public class Road {
    private final char start;
    private final char end;
    private final int travelTime;
    private final int carLimit;
    private double avgTime;
    private int weight = 0;
    private int carCounter = 0;
    private int trafficCounter = 0;


    public Road(char s, char e, int tt, int cl) {
        start = s;
        end = e;
        travelTime = tt;
        carLimit = cl;
        avgTime = travelTime;
    }

    public synchronized void notif() {
        notifyAll();
    }

    private synchronized void increaseCarCounter() {
        carCounter++;
    }

    private synchronized void decreaseCarCounter() {
        carCounter--;
    }

    private synchronized void updateAvg(long time){
        avgTime = (weight * avgTime + time + travelTime) / (weight + 1);
        weight++;
    }

    public void travel() throws InterruptedException {

        updateAvg(waitingTime());

        increaseCarCounter();
        Thread.sleep(travelTime);
        decreaseCarCounter();

        notif();
    }

    public synchronized long waitingTime() throws InterruptedException {

        long start = new Date().getTime();
        trafficCounter++;
        while (carCounter >= carLimit) {
            wait();
        }
        trafficCounter--;
        return new Date().getTime() - start;
    }

    public char getStart() {
        return start;
    }

    public char getEnd() {
        return end;
    }

    public double getAvgTime() {
        return avgTime + avgTime * trafficCounter;
    }

    @Override
    public String toString() {
        return start + " " + end + " " + avgTime + " " + carCounter + " " + weight;
    }
}
