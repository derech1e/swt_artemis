import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class EmptyLibraryTest {
    private Library library;
    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book("123-1-11", "Some Author", "Some Title");
        library = new Library();
    }

    @Test
    public void shouldBeEmpty() {
        Assertions.assertEquals(0, library.getStock().size(), "A new library shouldn't contain any books!");
    }

    @Test
    public void insertBookOnceReturnsTrue() {
        Assertions.assertTrue(library.insertBook(book), "Library.insertBook(…) should return true!");
    }

    @Test
    public void insertBookTwiceReturnsFalse() {
        library.insertBook(book);
        Assertions.assertFalse(library.insertBook(book),
                "Library.insertBook(…) of a book already in the stock should return false!");
    }

    @Test
    public void searchForIsbnReturnsNull() {
        Assertions.assertNull(library.searchForIsbn("123-1-11"),
                "Library.searchForIsbn(…) should return null if the Library is empty!");
    }

    @Test
    public void searchForAuthorReturnsEmptyCollection() {
        Assertions.assertTrue(library.searchForAuthor("Some Author").isEmpty(),
                "Library.searchForAuthor() should return an empty Collection if the Library is empty!");
    }
}
