package auto.austattung;

import auto.IAuto;

public class GPS extends Ausstattung{

    public GPS(IAuto auto) {
        super(auto);
    }

    @Override
    public int berechnePreis() {
        return 0;
    }

    @Override
    public void zeigeDetails() {

    }
}
