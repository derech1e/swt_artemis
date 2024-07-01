import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class EventCategoryTest {
	@Test
	public void testSize() {
		Assertions.assertEquals(3, EventCategory.values().length,
				"EventCategory should contain exactly three enum literals!");
	}

	@Test
	public void testOrder() {
		Assertions.assertTrue(EventCategory.EXHIBITION.compareTo(EventCategory.PRESENTATION) < 0,
				"Enum literal EXHIBITION should be declared before PRESENTATION!");
		Assertions.assertTrue(EventCategory.PRESENTATION.compareTo(EventCategory.SHOW) < 0,
				"Enum literal PRESENTATION should be declared before SHOW!");
	}
}
