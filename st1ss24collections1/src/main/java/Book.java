public class Book implements Comparable<Book>{

    private String isbn;
    private String author = "";
    private String title = "";

    public Book(String isbn, String author, String title) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
    }

    public Book(String isbn) {
        this(isbn, "", "");
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public int compareTo(Book b) {
        return this.getIsbn().compareTo(b.getIsbn());
    }
}
