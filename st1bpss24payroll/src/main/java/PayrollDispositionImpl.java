import java.util.*;

public class PayrollDispositionImpl implements PayrollDisposition {

    private Map<Employee, Double> payments = new HashMap<>();

    public PayrollDispositionImpl() {}

    public double getTotal() {
        double total = 0d;

        for(Map.Entry<Employee, Double> entries : payments.entrySet()) {
            total += entries.getValue();
        }
        return total;
    }

    public double getAverage() {
        double total = 0d;

        for(Map.Entry<Employee, Double> entries : payments.entrySet()) {
            total += entries.getValue();
        }
        if(total == 0) return 0;
        return total / payments.size();
    }

    public Map<Employee, Double> getPayments() {
        return this.payments;
    }
    
    @Override
    public void sendPayment(Employee empl, double payment) {
        if(empl == null) throw new NullPointerException();
        if(payment <= 0) throw new IllegalArgumentException();
        payments.put(empl, payment);
    }
}