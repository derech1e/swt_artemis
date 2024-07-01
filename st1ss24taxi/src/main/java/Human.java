public class Human {
    private String forename, name;

    public Human(String forename, String name) {
        this.forename = forename;
        this.name = name;
    }

    public String getForename() {
        return this.forename;
    }

    public String getName() {
        return this.name;
    }

    public String getFullName() {
        return this.forename + " " + this.name;
    }

    @Override
    public String toString() {
        return this.getFullName();
    }
}