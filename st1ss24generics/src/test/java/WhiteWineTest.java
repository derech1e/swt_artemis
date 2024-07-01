import drinks.WhiteWine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class WhiteWineTest {
    @Test
    public void whiteWineTestGetRegion() {
        WhiteWine whiteWine = new WhiteWine("Dresden");
        Assertions.assertEquals("Dresden", whiteWine.getRegion(), "drinks.WhiteWine.getRegion() should return the correct region!");
    }
}