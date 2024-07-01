public class Inhabitant {
    
    protected int income;
    
    public Inhabitant() {
        
    }
    
    public int taxableIncome() {
        return this.income;
    }
    
    public int tax() {
        return Math.max(1, ((int) Math.floor(this.taxableIncome() * 0.1)));
    }
    
    public void setIncome(int income) {
        if (income < 0) 
            throw new IllegalArgumentException("Income can't be negative!");
        this.income = income;
    }
    
}