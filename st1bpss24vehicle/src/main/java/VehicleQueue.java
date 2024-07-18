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
    private boolean prevState = false;

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
        greenLight = (currentTime / trafficLightRate) % 2 == 1;

        if(!prevState && greenLight) {
            prevState = true;
            lastExitTime = currentTime;
        }
        if(!greenLight && prevState) {
            prevState = false;
            lastEntryTime = currentTime;
        }

        System.out.println(String.format("TrafficLightTime: %s, EntryDelay: %s, ExitDelay: %s, Amount: %s, CurrentTime: %s", trafficLightRate, entryDelay, exitDelay, getSize(), currentTime));

        while (lastEntryTime + entryDelay <= currentTime) {
            enter();
            lastEntryTime += entryDelay;
            System.out.println("Current entry time: " + lastEntryTime);
        }

        if(greenLight) {
            while (lastExitTime + exitDelay <= currentTime) {
                leave();
                lastExitTime += exitDelay;
                System.out.println("Current exit time: " + lastExitTime);
            }
        }
    }
}
