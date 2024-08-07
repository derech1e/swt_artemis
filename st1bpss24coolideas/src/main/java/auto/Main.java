package auto;

import auto.austattung.AirBag;
import auto.austattung.GPS;

public class Main {

    public static void main(String[] args) {
        IAuto cabrio = new Cabrio();
        IAuto limousine = new Limousine();

        berechnePreis(cabrio);
        berechnePreis(limousine);


    }

    public static int berechnePreis(IAuto auto) {
        if(auto instanceof Cabrio)
            return -1;

        Limousine limousine = (Limousine) auto;
        limousine.test();
        return 0;
    }
}
