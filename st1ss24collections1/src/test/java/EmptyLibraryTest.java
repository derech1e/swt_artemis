import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class EmptyLibraryTest {
    private Library library;

    @BeforeEach
    public void setUp() {
        library = new Library();
    }

    @Test
    public void insertBookReturnsTrue() {
        Assertions.assertTrue(library.insertBook(new Book("123-1-11")), "Library.insertBook(…) should return true!");
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
