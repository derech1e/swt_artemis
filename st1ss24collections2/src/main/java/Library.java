import java.util.*;
import java.util.stream.Collectors;

public class Library {

    private Set<Book> stock = new TreeSet<>();

    public Library() {

    }

    public boolean insertBook(Book newBook) {
        if(newBook == null)
            throw new IllegalArgumentException("Book cannot be null");
        return this.stock.add(newBook);
    }

    public Book searchForIsbn(String isbn) {
        return this.stock.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    public Collection<Book> searchForAuthor(String author) {
        return this.stock.stream().filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public Collection<Book> getStock() {
        return stock;
    }
}