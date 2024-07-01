import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class BookTest {
	private Book book1;
	private Book book2;
	private Book book3;

	@BeforeEach
	public void setUp() {
		book1 = new Book("123-1-11", "Some Author", "Some Title");
		book2 = new Book("123-1-12", "Another Author", "Another Title");
		book3 = new Book("123-1-13");
	}

    @Test
    public void bookImplementsComparable() {
        Assertions.assertTrue(book1 instanceof Comparable, "Book must implement Comparable!");
    }

	@Test
	public void compareToSelfShouldReturnZero() {
		String message = "Book.compareTo(…) should recognise it as equal if a Book is compared to itself!";
		Assertions.assertEquals(0, book1.compareTo(book1), message);
		Assertions.assertEquals(0, book2.compareTo(book2), message);
		Assertions.assertEquals(0, book3.compareTo(book3), message);
	}

	@Test
	public void compareToLowerIsbnShouldBeNegative() {
		String message =
				"Book.compareTo(…) should consider a Book with a lower ISBN less than a Book with a higher ISBN!";
		Assertions.assertTrue(book1.compareTo(book2) < 0, message);
		Assertions.assertTrue(book1.compareTo(book3) < 0, message);
		Assertions.assertTrue(book2.compareTo(book3) < 0, message);
	}

	@Test
	public void compareToHigherIsbnShouldBePositive() {
		String message = "Book.compareTo(…) should consider a Book with a higher ISBN greater than a Book with a lower" +
				" ISBN!";
		Assertions.assertTrue(book2.compareTo(book1) > 0, message);
		Assertions.assertTrue(book3.compareTo(book1) > 0, message);
		Assertions.assertTrue(book3.compareTo(book2) > 0, message);
	}

	@Test
	public void compareToWithSameIsbnShouldBeZero() {
		String message = "Book.compareTo(…) should recognize Books with the same ISBN as equal!";
		Assertions.assertEquals(0, book1.compareTo(new Book("123-1-11")), message);
		Assertions.assertEquals(0, book2.compareTo(new Book("123-1-12")), message);
	}

	@Test
	public void compareToWithSameIsbnShouldBeZeroIgnoringOtherValues() {
		String message = "Book.compareTo(…) should compare Books using their ISBN only!";
		Assertions.assertEquals(0, book1.compareTo(new Book("123-1-11", "some value", "some value")), message);
		Assertions.assertEquals(0, book2.compareTo(new Book("123-1-12", "some value", "some value")), message);
	}

	@Test
	public void getIsbnShouldReturnIsbn() {
		String message = "Book.getIsbn() should return the ISBN of a book properly!";
		Assertions.assertEquals("123-1-11", book1.getIsbn(), message);
		Assertions.assertEquals("123-1-12", book2.getIsbn(), message);
		Assertions.assertEquals("123-1-13", book3.getIsbn(), message);
	}

	@Test
	public void getAuthorShouldReturnAuthor() {
		String message = "Book.getAuthor() should return the author of a book properly!";
		Assertions.assertEquals("Some Author", book1.getAuthor(), message);
		Assertions.assertEquals("Another Author", book2.getAuthor(), message);
	}

	@Test
	public void getAuthorShouldReturnEmptyString() {
		Assertions.assertEquals("", book3.getAuthor(),
				"Book.getAuthor() should return an empty String if the Book has been instantiated with the ISBN-only constructor!");
	}

	@Test
	public void getTitleShouldReturnTitle() {
		String message = "Book.getTitle() should return the title of a book properly!";
		Assertions.assertEquals("Some Title", book1.getTitle(), message);
		Assertions.assertEquals("Another Title", book2.getTitle(), message);
	}

	@Test
	public void getTitleShouldReturnEmptyString() {
		Assertions.assertEquals("", book3.getTitle(), "Book.getTitle() should return an empty String if the Book has been instantiated with the ISBN-only constructor!");
	}
}
