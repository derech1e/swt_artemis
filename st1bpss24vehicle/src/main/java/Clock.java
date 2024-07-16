import java.util.*;

public class Clock {

    private Set<ClockObserver> observers = new HashSet<>();
    private int currentTime = 0;
    private int endOfTime;

    public Clock(int endOfTime) {
        if (endOfTime <= 0) throw new IllegalArgumentException();
        this.endOfTime = endOfTime;
    }

    public void addObserver(ClockObserver observer) {
        if (observer == null) throw new NullPointerException();
        observers.add(observer);
    }

    public void removeObserver(ClockObserver observer) {
        if (observer == null) throw new NullPointerException();
        observers.remove(observer);
    }

    public int getCurrentTime() {
        return this.currentTime;
    }

    public void run() {
        currentTime = 0; // Reset time on each function call
        while (currentTime < endOfTime) {
            currentTime++;
            tick(currentTime);
        }
    }

    public void tick(int currentTime) {
        for (ClockObserver observer : observers) {
            observer.tick(currentTime);
        }
    }
}