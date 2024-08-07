import org.junit.jupiter.api.BeforeEach;

 
public class JContentTestBase {
    protected JContent jContent;

    @BeforeEach
    public void setUp() {
        jContent = new JContentImpl("title", "description");
    }

    protected static class JContentImpl extends JContent {
        public JContentImpl(String title, String description) {
            super(title, description);
        }

        @Override
        public String toString() {
            return "";
        }
    }
}
