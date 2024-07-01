
public class Taxi {
    private Human driver;
    private Human[] passengers;

    public Taxi(Human driver) {
        this.driver = driver;
        this.passengers = new Human[4];
    }

    public void add(Human passenger) {
        if (this.getPassengerCount() == 4) {
            System.out.println("We are sorry, Lucky Fuke. The taxi is full.");
            return;
        }
        for (int i = 0; i < this.passengers.length; i++) {
            if (this.passengers[i] != null)
                continue;
            this.passengers[i] = passenger;
            break;
        }
        System.out.println(String.format("%s gets in.", passenger.getFullName()));
    }

    public String getDriverName() {
        return this.driver.getFullName();
    }

    private int getPassengerCount() {
        int counter = 0;
        for (Human passenger : this.passengers) {
            if (passenger != null) {
                counter++;
            }
        }
        return counter;
    }

    public String getStatus() {
        int passengerCount = this.getPassengerCount();
        return switch (passengerCount) {
            case 0 -> String.format("This is the taxi of %s. He takes nobody along.", this.getDriverName());
            case 1 ->
                    String.format("This is the taxi of %s. He takes %s along.", this.getDriverName(), this.passengers[0].getFullName());
            case 2 ->
                    String.format("This is the taxi of %s. He takes %s and %s along.", this.getDriverName(), this.passengers[0].getFullName(), this.passengers[1].getFullName());
            case 3 ->
                    String.format("This is the taxi of %s. He takes %s, %s and %s along.", this.getDriverName(), this.passengers[0].getFullName(), this.passengers[1].getFullName(), this.passengers[2].getFullName());
            case 4 ->
                    String.format("This is the taxi of %s. He takes %s, %s, %s and %s along.", this.getDriverName(), this.passengers[0].getFullName(), this.passengers[1].getFullName(), this.passengers[2].getFullName(), this.passengers[3].getFullName());
            default -> "";
        };
    }

    public Human[] allGetOut() {
        if (this.getPassengerCount() == 0)
            return new Human[0];

        Human[] leftPassengers = new Human[this.getPassengerCount()];
        for(int i = 0; i < this.passengers.length; i++) {
            if(passengers[i] != null)
                leftPassengers[i] = this.passengers[i];
        }

        this.passengers = new Human[4];
        return leftPassengers;
    }

    @Override
    public String toString() {
        return this.getStatus();
    }
}