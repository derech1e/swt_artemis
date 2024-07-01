public class Noble extends Inhabitant {
    
    @Override
    public int tax() {
        return Math.max(super.tax(), 20);
    }
}