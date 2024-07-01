import drinks.RedWine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class RedWineTest {
    @Test
    public void redWineTestGetRegion() {
        RedWine redWine = new RedWine("Dresden");
        Assertions.assertEquals("Dresden", redWine.getRegion(), "drinks.RedWine.getRegion() should return the correct region!");
    }
}