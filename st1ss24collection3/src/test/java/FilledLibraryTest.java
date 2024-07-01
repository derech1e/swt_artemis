import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import collections3.Book;
import collections3.Library;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class FilledLibraryTest {
    private Library library;

    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;

    @BeforeEach
    public void setUp() {
        library = new Library();
        book1 = new Book("123-1-11", "Author A", "Title 1");
        book2 = new Book("123-1-12", "Author B", "Title 2");
        book3 = new Book("123-1-13", "Author C", "Title 3");
        book4 = new Book("123-1-14", "Author D", "Title 4");
        book5 = new Book("123-1-15", "Author C", "Title 5");
        library.insertBook(book4);
        library.insertBook(book5);
        library.insertBook(book1);
        library.insertBook(book3);
        library.insertBook(book2);
    }

    @Test
    public void getStockReturnsAllBooksOrderedByIsbn() {
        List<Book> actual = List.copyOf(library.getStock());
        List<Book> expected = List.of(book1, book2, book3, book4, book5);
        String message = "The stock returned by collections3.Library.getStock() should contain all books ordered by ISBN!";
        Assertions.assertEquals(expected, actual, message);
    }

    @Test
    public void searchForIsbnReturnsCorrectBook() {
        Assertions.assertSame(book1, library.searchForIsbn("123-1-11"),
                "collections3.Library.searchForIsbn(…) should return the correct collections3.Book!");
        Assertions.assertSame(book2, library.searchForIsbn("123-1-12"),
                "collections3.Library.searchForIsbn(…) should return the correct collections3.Book!");
        Assertions.assertSame(book3, library.searchForIsbn("123-1-13"), "collections3.Library.searchForIsbn(…) should return the " +
                "correct collections3.Book!");
        Assertions.assertSame(book4, library.searchForIsbn("123-1-14"), "collections3.Library.searchForIsbn(…) should return the correct collections3.Book!");
        Assertions.assertSame(book5, library.searchForIsbn("123-1-15"), "collections3.Library.searchForIsbn(…) should return the correct collections3.Book!");
    }

    @Test
    public void searchForIsbnReturnsNullIfNotFound() {
        Assertions.assertNull(library.searchForIsbn("5"), "collections3.Library.searchForIsbn(…) should return null if there is no collections3.Book with the specified ISBN!");
    }

    @Test
    public void searchForAuthorReturnsMatchingBook() {
        Collection<Book> searchResult = library.searchForAuthor("Author D");
        Assertions.assertTrue(searchResult.contains(book4),
                "collections3.Library.searchForAuthor(…) does not return all of the given author's books!");
        Assertions.assertEquals(1, searchResult.size(), "collections3.Library.searchForAuthor(…) should only return the books of the given author!");
    }

    @Test
    public void searchForAuthorReturnsMatchingBooksOrderedByIsbn() {
        List<Book> actual = List.copyOf(library.searchForAuthor("Author C"));
        List<Book> expected = List.of(book3, book5);
        Assertions.assertEquals(expected, actual, "collections3.Library.searchForAuthor(…) should return all of the given author's books ordered by ISBN!");
    }

    @Test
    public void searchForAuthorReturnsEmptyCollectionIfNotFound() {
        Collection<Book> searchResult = library.searchForAuthor("Author");
        Assertions.assertTrue(searchResult.isEmpty(), "collections3.Library.searchForAuthor(…) should return an empty Collection if the collections3.Library does not contain any book of the given author!");
    }

    @Test
    public void listStockByAuthorReturnsAllBooksOfEachAuthor() {
        Map<String, Set<Book>> expected = Map.of( //
                "Author A", Set.of(book1), //
                "Author B", Set.of(book2), //
                "Author C", Set.of(book3, book5), //
                "Author D", Set.of(book4));
        Map<String, Set<Book>> actual = library.listStockByAuthor();
        Assertions.assertEquals(expected, actual, "collections3.Library.listStockByAuthor() should return all authors with all their books!");
    }
}
