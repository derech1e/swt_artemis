import static java.lang.reflect.Modifier.isPrivate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class PrivateLibraryTest {
    @Test
    public void stockShouldBeOfTypeSet() {
        Assertions.assertEquals(Set.class, resolveStockField().getType(), "Library.stock should be of type java.util.Set!");
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
