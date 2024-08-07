public abstract class ProjectItem {
    private String name;
    private String details;
    private double rate;

    public ProjectItem(String name, String details, double rate) {
        if (name == null || details == null) throw new NullPointerException();
        if (name.isEmpty() || details.isEmpty() || rate < 0) throw new IllegalArgumentException();
        this.name = name;
        this.details = details;
        this.rate = rate;
    }

    public void setDetails(String newDetails) {
        if(newDetails == null) throw new NullPointerException();
        if(newDetails.isEmpty()) throw new IllegalArgumentException();
        this.details = newDetails;
    }

    public long getCostEstimate() {
        return Math.round(rate * getTimeRequired() + getMaterialCost());
    }

    public abstract double getTimeRequired();

    public abstract long getMaterialCost();
}