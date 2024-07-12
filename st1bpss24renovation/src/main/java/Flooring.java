public class Flooring extends Material {
    private static double limit = 0.02;
    private double widthOfFlooring;

    public Flooring(String name, double price, double width) {
        super(name, price);
        if(width <= 0) throw new IllegalArgumentException();
        this.widthOfFlooring = width;
        
    }

    public double getWidth() {
        return this.widthOfFlooring;
    }

    public int getMaterialRequirements(Surface surface) {
        if(surface == null) throw new NullPointerException();
        return -1; // TODO
    }
}