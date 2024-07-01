public class BankAccount {
    private double balance = 0.0;
    private double lineOfCredit;
    private String accountNumber;

    private AccountState state = new Positive();

    public BankAccount(String accountNumber, double lineOfCredit) {
        if(accountNumber == null) throw new NullPointerException();
        if(accountNumber.isEmpty()) throw new IllegalArgumentException();

        this.accountNumber = accountNumber;
        this.lineOfCredit = lineOfCredit;
    }

    public boolean payIn(double amount) {
        if(amount <= 0) throw new IllegalArgumentException();
        return state.payIn(amount);
    }

    public boolean payOff(double amount) {
        if(amount <= 0) throw new IllegalArgumentException();
        return state.payOff(amount);
    }

    public boolean close() {
        if(this.getBalance() == 0 && !(state instanceof Closed)) {
            state = new Closed();
            return true;
        }
        return false;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getState() {
        return this.state.toString();
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void printBalance() {
        this.state.printBalance();
    }

    public void payInterest() {
        state.payInterest();
    }

    private abstract class AccountState {

        public boolean payIn(double amount) {
            if(amount <= 0) throw new IllegalArgumentException();
            return false;
        }

        public boolean payOff(double amount) {
            if(amount <= 0) throw new IllegalArgumentException();
            return false;
        }

        @Override
        public String toString() {
            if (this instanceof Positive) {
                return "Positive";
            } else if (this instanceof Negative) {
                return "Negative";
            } else if (this instanceof Frozen) {
                return "Frozen";
            } else if (this instanceof Closed) {
                return "Closed";
            }
            return "";
        }

        abstract void printBalance();

        public void payInterest() {
            throw new IllegalStateException();
        }

    }

    private class Positive extends AccountState {

        @Override
        public boolean payIn(double amount) {
            balance += amount;
            return true;
        }

        @Override
        public boolean payOff(double amount) {
            double newBalance = balance - amount;

            if(newBalance < 0 && Math.abs(newBalance) < lineOfCredit)
                state = new Negative();

            if(Math.abs(newBalance) == lineOfCredit)
                state = new Frozen();

            else if(Math.abs(newBalance) > lineOfCredit)
                return false;

            balance -= amount;
            return true;
        }

        @Override
        public void printBalance() {
            System.out.printf("Balance is %s: %s%n", getState().toUpperCase(), "+" + getBalance() + ".");
        }

        @Override
        public void payInterest() {
            balance *= 1.01;
        }
    }

    private class Negative extends AccountState {

        @Override
        public boolean payIn(double amount) {
            balance += amount;
            if(balance >= 0)
                state = new Positive();
            return true;
        }

        @Override
        public boolean payOff(double amount) {
            double newBalance = balance - amount;

            if(Math.abs(newBalance) == lineOfCredit)
                state = new Frozen();

            else if(Math.abs(newBalance) > lineOfCredit)
                return false;

            balance -= amount;
            return true;
        }

        @Override
        public void printBalance() {
            System.out.printf("Balance is %s: %s%n", getState().toUpperCase(), getBalance() + ".");
        }

        @Override
        public void payInterest() {
            balance *= 1.03;

            if(balance <= -lineOfCredit)
                state = new Frozen();
        }
    }

    private class Frozen extends AccountState {

        @Override
        public boolean payIn(double amount) {
            double newBalance = balance + amount;

            if(newBalance >= 0)
                state = new Positive();
            else
                state = new Negative();
            balance = newBalance;
            return true;
        }

        @Override
        public void printBalance() {
            System.out.printf("Balance is %s: %s%n", "NEGATIVE", getBalance() + ". You need to pay in money.");
        }

        @Override
        public void payInterest() {
            balance *= 1.05;
        }
    }

    private class Closed extends AccountState {
        @Override
        public void printBalance() {
            System.out.printf("This account is %s. The balance is 0.", getState().toUpperCase());
        }
    }
}