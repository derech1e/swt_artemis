public class Paint extends Material {
    private static double limit = 0.02;
    private int numberOfCoats;
    private double squareMetersPerLiter;

    public Paint(String name, double price, int numberOfCoats, double squareMetersPerLiter) {
        super(name, price);
        if(numberOfCoats <= 0 || squareMetersPerLiter <= 0) throw new IllegalArgumentException();
        this.numberOfCoats = numberOfCoats;
        this.squareMetersPerLiter = squareMetersPerLiter;
    }

    public int getNumberOfCoats() {
        return numberOfCoats;
    }

    public double getSquareMetersPerLiter() {
        return this.squareMetersPerLiter;
    }

    @Override
    public int getMaterialRequirements(Surface surface) {
        if(surface == null) throw new NullPointerException();
        double cost = surface.getArea() * getNumberOfCoats() / getSquareMetersPerLiter() - limit;
        return (int) (Math.floor(2 * cost) + 1);
    }
}