package drinks;

public abstract class Wine extends Drink {

    private String region;

    public Wine(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
