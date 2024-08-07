public class JValuation extends JContent {

    public JValuation(String title, String description) {
        super(title, description);
    }

    @Override
    public String toString() {
        return String.format("Valuation: %s\n%s", this.getTitle(), this.getDescription());
    }
}