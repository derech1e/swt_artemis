public class Payroll {
    private int payday;
    private PayrollDisposition disposition;

    public Payroll(PayrollDisposition disposition, int payday) {
        if (disposition == null) throw new NullPointerException();
        if (payday <= 0 || payday > 30) throw new IllegalArgumentException();
        this.payday = payday;
        this.disposition = disposition;
    }

    public void doPayroll(PayrollDB db) {
        for (Employee empl : db.getEmployeeList()) {
            try {
                if(!empl.isPayday(payday)) continue;
                disposition.sendPayment(empl, empl.calculatePay() - empl.calculateDeductions());
            } catch (UnpayableEmployeeException ignored) {

            }
        }
    }
}