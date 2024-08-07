public class JTopic extends JContent {

    private int id;

    public JTopic(String title, String description, int id) {
        super(title, description);
        if (id <= 0) throw new IllegalArgumentException();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("The topic %s has been updated!", id);
    }
}