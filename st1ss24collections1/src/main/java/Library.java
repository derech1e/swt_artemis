import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private List<Book> stock = new ArrayList<>();

    public Library() {

    }

    public boolean insertBook(Book newBook) {
        if(newBook == null)
            throw new IllegalArgumentException("Book cannot be null");
        this.stock.add(newBook);
        Collections.sort(this.stock);
        return true;
    }

    public Book searchForIsbn(String isbn) {
        int idx = Collections.binarySearch(stock, new Book(isbn));
        if(idx < 0) {
            return null;
        }
        return this.stock.get(idx);
    }

    public Collection<Book> searchForAuthor(String author) {
        return this.stock.stream().filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
    }
}