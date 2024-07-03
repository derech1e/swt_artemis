public class Appointee extends Employee {

    private int payday;
    private int hoursPerMonth;
    private double payPerHour;

    public Appointee(String id, int payday, int hoursPerMonth, double payPerHour) {
        super(id);
        if(payday <= 0 || payday > 30 || hoursPerMonth <= 0 || payPerHour <= 0) throw new IllegalArgumentException();
        this.payday = payday;
        this.hoursPerMonth = hoursPerMonth;
        this.payPerHour = payPerHour;
    }

    @Override
    public boolean isPayday(int dayOfMonth) {
        if(dayOfMonth < 1 || dayOfMonth > 30) throw new IllegalArgumentException();
        return dayOfMonth == payday;
    }

    @Override
    public double calculatePay() {
        return this.hoursPerMonth * this.payPerHour;
    }

    @Override
    public double calculateDeductions() {
        return this.calculatePay() * 0.4;
    }
}