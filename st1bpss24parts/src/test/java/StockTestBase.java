import org.junit.jupiter.api.BeforeEach;

 
public class StockTestBase {
    protected Stock stock;
    protected Part part;

    @BeforeEach
    public void setUp() {
        stock = new Stock() {
            // instantiation of an anonymous inner subclass of Stock
        };
        part = new SingleComponent("0", "singComp");

        stock.insert(part, 15);
    }


}
