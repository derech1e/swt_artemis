import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class EventTest {
    private Event event;

    @BeforeEach
    public void setUp() {
        event = new Event("Science Slam", EventCategory.PRESENTATION);
    }

    @Test
    public void eventTestConstructorNullArgument() {
        try {
            new Event(null, EventCategory.EXHIBITION);
            fail("Event.Event() should throw a NullPointerException if the title argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new Event("Technical Collections", null);
            fail("Event.Event() should throw a NullPointerException if the category argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void eventTestConstructorIllegalArgument() {
        try {
            new Event("", EventCategory.EXHIBITION);
            fail("Event.Event() should throw an IllegalArgumentException if the title argument is empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void eventTestGetTitle() {
        assertEquals("Science Slam", event.getTitle(),
                "Event.getTitle() should return the correct value!");
    }

    @Test
    public void eventTestGetCategory() {
        assertEquals(EventCategory.PRESENTATION, event.getCategory(),
                "Event.getCategory() should return the correct value!");
    }

    @Test
    public void eventTestCompareToNullArgument() {
        try {
            event.compareTo(null);
            fail("Event.compareTo() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void eventsWithEqualTitleAndCategoryShouldCompareEqual() {
        Event myEvent = new Event("My Event", EventCategory.EXHIBITION);
        assertEquals(0, myEvent.compareTo(myEvent),
                "Event.compareTo(…) should return 0 when comparing the same object!");
        assertEquals(0,
                myEvent.compareTo(
                        new Event("My Event", EventCategory.EXHIBITION)),
                "Event.compareTo(…) should return 0 if compared event has equal title and category!");
    }

    @Test
    public void eventsWithEqualTitleAreComparedByCategory() {
        Event lowest = new Event("My Event", EventCategory.EXHIBITION);
        Event middle = new Event("My Event", EventCategory.PRESENTATION);
        Event highest = new Event("My Event", EventCategory.SHOW);

        String message1 = "Event.compareTo(…) should return a negative value if the given "
                + "event has the same title but a higher category!";

        assertTrue(lowest.compareTo(middle) < 0, message1);
        assertTrue(lowest.compareTo(highest) < 0, message1);
        assertTrue(middle.compareTo(highest) < 0, message1);

        String message2 = "Event.compareTo(…) should return a positive value if the given "
                + "event has the same title but a lower category!";

        assertTrue(highest.compareTo(lowest) > 0, message2);
        assertTrue(highest.compareTo(middle) > 0, message2);
        assertTrue(middle.compareTo(lowest) > 0, message2);
    }

    @Test
    public void eventsWithUnequalTitleAreComparedByTitle() {
        Event lowest = new Event("Event A", EventCategory.SHOW);
        Event middle = new Event("Event B", EventCategory.PRESENTATION);
        Event highest = new Event("Event C", EventCategory.EXHIBITION);

        String message1 = "Event.compareTo(…) should return a negative value if the given "
                + "event has a higher title (e.g., 'Event A' < 'Event B')!";

        assertTrue(lowest.compareTo(middle) < 0, message1);
        assertTrue(lowest.compareTo(highest) < 0, message1);
        assertTrue(middle.compareTo(highest) < 0, message1);

        String message2 = "Event.compareTo(…) should return a positive value if the given "
                + "event has a lower title (e.g., 'Event B' > 'Event A')!";

        assertTrue(highest.compareTo(lowest) > 0, message2);
        assertTrue(highest.compareTo(middle) > 0, message2);
        assertTrue(middle.compareTo(lowest) > 0, message2);
    }

    @Test
    public void selfEquality() {
        // type Object is intentional (to test correct override)
        Object event = new Event("Event X", EventCategory.SHOW);
        if (!event.equals(event)) {
            fail("Event.equals(…) should regard the same object as equal.");
        }
    }

    @Test
    public void unequalEventsHashCodeAndEquals() {
        // type Object is intentional (to test correct override)
        Object event1 = new Event("Event A", EventCategory.SHOW);
        Object event2 = new Event("Event A", EventCategory.EXHIBITION);
        Object event3 = new Event("Event B", EventCategory.EXHIBITION);
        if (event1.equals(event2) || event2.equals(event3)) {
            fail("Event.equals(…) should not treat different objects as being equal.");
        }
        if (event1.hashCode() == event2.hashCode()
                || event2.hashCode() == event3.hashCode()) {
            fail("Event.hashCode() should not return the same hashcode for different objects.");
        }
    }

    @Test
    public void equalEventsHashCodeAndEquals() {
        // type Object is intentional (to test correct override)
        Object event1 = new Event("Event A", EventCategory.EXHIBITION);
        Object event2 = new Event("Event A", EventCategory.EXHIBITION);
        if (!event1.equals(event2)) {
            fail("Event.equals(…) should regard equal objects (equal title and category) as equal.");
        }
        if (event1.hashCode() != event2.hashCode()) {
            fail("Event.hashCode() of equal objects (equal title and category) should return the same hashcode.");
        }
    }

    @Test
    public void unequalToOtherTypes() {
        // type Object is intentional (to test correct override)
        Object event = new Event("Event X", EventCategory.SHOW);
        try {
            if (event.equals("not an event")) {
                fail("Event.equals(…) should return false when comparing to objects of another type.");
            }
            if (event.equals(null)) {
                fail("Event.equals(…) should return false when comparing to null.");
            }
        } catch (Exception e) {
            fail("Event.equals(…) should not throw an "
                    + e.getClass().getSimpleName()
                    + " when the compared object is not of the same type or when it is null.");
        }
    }
}
