package collections3;

public class Book implements Comparable<Book> {

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

    @Override
    public boolean equals(Object object) {
        if (object instanceof Book compareBook) {
            if (this == object) return true;
            return this.getIsbn().equals(compareBook.getIsbn());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getIsbn().hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s by %s (ISBN %s)", this.getTitle(), this.getAuthor(), this.getIsbn());
    }

    public int compareTo(Book b) {
        return this.getIsbn().compareTo(b.getIsbn());
    }
}
