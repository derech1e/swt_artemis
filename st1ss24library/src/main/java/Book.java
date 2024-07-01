public class Book {

    private String title;

    public Book(String title) {
        this.title = title;
        System.out.println(String.format("Book %s created.", this.title));
    }

    @Override
    public String toString() {
        return this.title;
    }

}