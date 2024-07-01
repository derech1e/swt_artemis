import static java.lang.reflect.Modifier.isPrivate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class PrivateLibraryTest {
    private Library library;

    @BeforeEach
    public void setUp() {
        library = new Library();
        library.insertBook(new Book("321-1-125"));
        library.insertBook(new Book("321-1-122"));
        library.insertBook(new Book("321-1-124"));
    }

    @Test
    public void stockShouldBeOrderedByIsbn() throws ReflectiveOperationException {
        List<Book> stock = getStockField();
        Assertions.assertEquals(3, stock.size(), "Library.stock doesn't contain the right number of books!");
        String message = "Books inserted into the stock should be ordered by their ISBN!";
        Assertions.assertEquals("321-1-122", stock.get(0).getIsbn(), message);
        Assertions.assertEquals("321-1-124", stock.get(1).getIsbn(), message);
        Assertions.assertEquals("321-1-125", stock.get(2).getIsbn(), message);
    }

    @Test
    public void stockShouldBeOfTypeList() {
        Assertions.assertEquals(List.class, resolveStockField().getType(), "Library.stock should be of type java.util.List!");
    }

    @Test
    public void stockShouldBePrivate() {
        Assertions.assertTrue(isPrivate(resolveStockField().getModifiers()), "Library.stock should be private!");
    }

    @Test
    public void returnTypeOfSearchForAuthorShouldBeCollection() {
        Method searchForAuthorMethod = resolveSearchForAuthorMethod();
        Assertions.assertEquals(Collection.class, searchForAuthorMethod.getReturnType(),
                "The return type of searchForAuthor(…) should be Collection<Book>!");
    }

    @SuppressWarnings("unchecked")
    private List<Book> getStockField() throws ReflectiveOperationException {
        Field stockField = resolveStockField();
        stockField.setAccessible(true);
        return (List<Book>) stockField.get(library);
    }

    private static Field resolveStockField() {
        try {
            return Library.class.getDeclaredField("stock");
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Library should have an attribute named stock!");
        }
    }

    private static Method resolveSearchForAuthorMethod() {
        try {
            return Library.class.getDeclaredMethod("searchForAuthor", String.class);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Library should have a searchForAuthor(…) method!");
        }
    }
}
