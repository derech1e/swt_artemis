import java.util.*;

public class VehicleQueue implements ClockObserver {

    private double entryDelay;
    private double exitDelay;
    private int trafficLightRate; // Duration of light (green/ red) phase in seconds
    private boolean greenLight = false;
    private VehicleGenerator generator;
    private List<Vehicle> queue = new ArrayList<>();
    private double lastEntryTime = 0; // Last time a vehicle entered
    private double lastExitTime = 0; // Last time a vehicle exited
    private boolean prevGreenLight = false;

    public VehicleQueue(double entryDelay, double exitDelay, int trafficLightRate, VehicleGenerator generator) {
        if (entryDelay <= 0 || exitDelay <= 0 || trafficLightRate <= 0) throw new IllegalArgumentException();
        if (generator == null) throw new NullPointerException();
        this.entryDelay = entryDelay;
        this.exitDelay = exitDelay;
        this.trafficLightRate = trafficLightRate;
        this.generator = generator;
    }

    public void enter() {
        queue.add(generator.createVehicle());
    }

    public void leave() {
        if (!queue.isEmpty()) {
            queue.remove(0);
        }
    }

    public double getLength() {
        double length = 0;
        for (Vehicle item : queue) {
            length += item.getLength();
        }
        return length;
    }

    public int getSize() {
        return queue.size();
    }

    @Override
    public void tick(int currentTime) {
        int phaseIndex = (currentTime / trafficLightRate); // Determine current phase index

        // Determine if it's a green light phase
        greenLight = phaseIndex % 2 == 1;
        System.out.println(greenLight);

        if(greenLight) {
            lastExitTime -= exitDelay;
        }

        if (currentTime >= lastEntryTime + entryDelay) {
            if(lastEntryTime >= 0) {
                enter();
            }
            System.out.println("Vehicle entered at time: " + currentTime);
            lastEntryTime = currentTime;
        }

        if (greenLight && currentTime >= lastExitTime + exitDelay) {
            if(lastExitTime > 0) {
                leave();
            }
            System.out.println("Vehicle left at time: " + currentTime);
            lastExitTime = currentTime;
        }
        prevGreenLight = greenLight;
        System.out.println("Current queue size at time " + currentTime + ": " + getSize());
    }



}
