public class Serf extends Peasant {
    
    @Override
    public int taxableIncome() {
        if (this.income <= 12) {
            return 0;
        }
        return this.income - 12;
    }
    
}