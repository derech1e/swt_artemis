package collections3;

import java.util.*;
import java.util.stream.Collectors;

public class Library {

    private Map<String, Set<Book>> stock = new TreeMap<>();

    public Library() {

    }

    public boolean insertBook(Book newBook) {
        if (newBook == null)
            throw new IllegalArgumentException("collections3.Book cannot be null");

        return !stock.getOrDefault(newBook.getAuthor(), new TreeSet<>()).contains(newBook)
                && stock.computeIfAbsent(newBook.getAuthor(), a -> new TreeSet<>()).add(newBook);
    }

    public Book searchForIsbn(String isbn) {
        return this.stock.values().stream().flatMap(Set::stream).filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    public Collection<Book> searchForAuthor(String author) {
        return this.stock.entrySet().stream().filter((key) -> key.getKey().equals(author)).map(Map.Entry::getValue).findFirst().orElse(Collections.emptySet());
    }

    public Map<String, Set<Book>> listStockByAuthor() {
        return this.stock;
    }

    public Collection<Book> getStock() {
        return this.stock.values().stream().flatMap(Set::stream).collect(Collectors.toCollection(TreeSet::new));
    }
}