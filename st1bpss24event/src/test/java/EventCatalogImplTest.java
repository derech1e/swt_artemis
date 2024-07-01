import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class EventCatalogImplTest {
    private EventCatalog catalog;
    private List<Event> events;
    private List<List<Time>> times;
    private Map<EventCategory, List<Integer>> categoryToIndices;
    private Event newEvent;

    @BeforeEach
    public void setUp() {
        catalog = new EventCatalogImpl();
        events = new ArrayList<>();
        times = new ArrayList<>();
        categoryToIndices = new HashMap<>();

        events.add(new Event("e1", EventCategory.EXHIBITION));
        events.add(new Event("e2", EventCategory.EXHIBITION));
        categoryToIndices.put(EventCategory.EXHIBITION, List.of(0, 1));
        events.add(new Event("p1", EventCategory.PRESENTATION));
        events.add(new Event("p2", EventCategory.PRESENTATION));
        events.add(new Event("p3", EventCategory.PRESENTATION));
        categoryToIndices.put(EventCategory.PRESENTATION, List.of(2, 3, 4));
        events.add(new Event("s1", EventCategory.SHOW));
        events.add(new Event("s2", EventCategory.SHOW));
        events.add(new Event("s3", EventCategory.SHOW));
        categoryToIndices.put(EventCategory.SHOW, List.of(5, 6, 7));

        times.add(List.of(new Time(10, 30), new Time(13, 30), new Time(16, 30)));
        times.add(List.of(new Time(11, 00), new Time(15, 00)));
        times.add(List.of(new Time(14, 15), new Time(16, 45), new Time(19, 15)));
        times.add(List.of(new Time(10, 00), new Time(12, 40), new Time(14, 00)));
        times.add(List.of(new Time(11, 10), new Time(13, 30), new Time(16, 10)));
        times.add(List.of());
        times.add(List.of(new Time(11, 45), new Time(12, 45), new Time(13, 45)));
        times.add(List.of(new Time(17, 20), new Time(18, 50), new Time(20, 15)));

        for (int i = 0; i < events.size(); i++) {
            catalog.addCatalogEntry(events.get(i), new HashSet<>(times.get(i)));
        }

        newEvent = new Event("e3", EventCategory.EXHIBITION);
    }

    @Test
    public void testSuperClass() {
        assertEquals(EventCatalogImpl.class.getSuperclass(), TreeMap.class,
                "The class EventCatalogImpl should extend the class TreeMap!");
    }

    @Test
    public void testAddCatalogEntryNullArgument() {
        var nullSet = new HashSet<>(times.get(0));
        nullSet.add(null);

        try {
            catalog.addCatalogEntry(null, new HashSet<>(times.get(0)));
            fail("EventCatalogImpl.addCatalogEntry() should throw a NullPointerException if the e argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            catalog.addCatalogEntry(events.get(0), null);
            fail("EventCatalogImpl.addCatalogEntry() should throw a NullPointerException if the tSet argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            catalog.addCatalogEntry(events.get(0), nullSet);
            fail(
                    "EventCatalogImpl.addCatalogEntry() should throw a NullPointerException if one of the objects in the tSet argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testAddCatalogEntry() {
        assertFalse(catalog.addCatalogEntry(events.get(0), new HashSet<>(times.get(1))),
                "EventCatalogImpl.addCatalogEntry() should return false if the catalog already contains the Event!");
        assertEquals(new HashSet<>(times.get(0)), catalog.getTimesOfEvent(events.get(0)),
                "EventCatalogImpl.addCatalogEntry() should not change the Event's catalog entry if the catalog already contains the Event!");

        assertTrue(catalog.addCatalogEntry(newEvent, new HashSet<>(times.get(6))),
                "EventCatalogImpl.addCatalogEntry() should return true if the catalog did not contain the Event!");
        assertEquals(new HashSet<>(times.get(0)), catalog.getTimesOfEvent(events.get(0)), "EventCatalogImpl.addCatalogEntry() should add the Event-Set<Time> pair!");
    }

    @Test
    public void testAddTimeToEventNullArgument() {
        try {
            catalog.addTimeToEvent(null, times.get(0).get(0));
            fail("EventCatalogImpl.addTimeToEvent() should throw a NullPointerException if the e argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            catalog.addTimeToEvent(events.get(0), null);
            fail("EventCatalogImpl.addTimeToEvent() should throw a NullPointerException if the t argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testAddTimeToEvent() {
        assertFalse(catalog.addTimeToEvent(events.get(0), times.get(0).get(0)), "EventCatalogImpl" +
                ".addTimeToEvent() should return false if the Time is already registered for the Event!");

        assertFalse(catalog.addTimeToEvent(newEvent, times.get(1).get(0)),
                "EventCatalogImpl.addTimeToEvent() should return false if the catalog does not contain the Event!");
        assertFalse(catalog.getAllEvents().contains(newEvent),
                "EventCatalogImpl.addTimeToEvent() should not add the Event if the catalog did not contain it!");

        var newTimes = new HashSet<>(times.get(0));
        newTimes.add(times.get(1).get(0));

        assertTrue(catalog.addTimeToEvent(events.get(0), times.get(1).get(0)),
                "EventCatalogImpl.addTimeToEvent() should return true if the Time was not registered for the Event!");
        assertEquals(newTimes, catalog.getTimesOfEvent(events.get(0)), "EventCatalogImpl.addTimeToEvent() should actually add the Time!");
    }

    @Test
    public void testGetAllEvents() {
        assertEquals(new HashSet<>(events), catalog.getAllEvents(),
                "EventCatalogImpl.getAllEvents() should return a Set containing all the Events added to the catalog!");
    }

    @Test
    public void testGetTimesOfEventNullArgument() {
        try {
            catalog.getTimesOfEvent(null);
            fail("EventCatalogImpl.getTimesOfEvent() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testGetTimesOfEvent() {
        assertNull(catalog.getTimesOfEvent(newEvent),
                "EventCatalogImpl.getTimesOfEvent() should return null if the catalog does not contain the event!");

        for (int i = 0; i < events.size(); i++) {
            assertEquals(new HashSet<>(times.get(i)), catalog.getTimesOfEvent(events.get(i)),
                    "EventCatalogImpl.getTimesOfEvent() should return the correct Set<Time>!");
        }
    }

    @Test
    public void testFilterByEventCategoryNullArgument() {
        try {
            catalog.filterByEventCategory(null);
            fail("EventCatalogImpl.filterByEventCategory() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testFilterByEventCategoryEmptyCategory() {
        for (Event e : new HashSet<>(catalog.getAllEvents())) {
            if (e.getCategory() == EventCategory.EXHIBITION) {
                catalog.deleteEvent(e);
            }
        }
        Map<Event, Set<Time>> actual = catalog.filterByEventCategory(EventCategory.EXHIBITION);
        assertThat(actual) //
                .as("EventCatalogImpl.filterByEventCategory() should return an empty map "
                        + "if there are no events of the given category.") //
                .isEmpty();
    }

    @Test
    public void testFilterByEventCategory() {
        for (var entry : categoryToIndices.entrySet()) {
            Map<Event, Set<Time>> actual = catalog
                    .filterByEventCategory(entry.getKey());

            if (actual == null) {
                fail("EventCatalogImpl.filterByEventCategory() should never return null.");
            }

            for (var index : entry.getValue()) {
                var event = events.get(index);
                assertThat(actual) //
                        .as("EventCatalogImpl.filterByEventCategory() should return a map containing all matching events.") //
                        .containsKey(event);
                assertThat(actual.get(event)) //
                        .as("EventCatalogImpl.filterByEventCategory() should return a map containing all matching events "
                                + "together with all associated times.") //
                        .containsAll(times.get(index));
            }

            assertThat(actual) //
                    .as("EventCatalogImpl.filterByEventCategory() should return a map containing only the matching events.")
                    .hasSize(entry.getValue().size());
        }
    }

    @Test
    public void testDeleteEventNullArgument() {
        try {
            catalog.deleteEvent(null);
            fail("EventCatalogImpl.deleteEvent() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testDeleteEvent() {
        assertNull(catalog.deleteEvent(newEvent), "EventCatalogImpl.deleteEvent() should return null if the catalog does not contain the Event!");

        for (int i = 0; i < events.size(); i++) {
            assertEquals(new HashSet<>(times.get(i)), catalog.deleteEvent(events.get(i)), "EventCatalogImpl.deleteEvent() should return the correct Set<Time>!");
            assertTrue(!catalog.getAllEvents().contains(events.get(i)) && catalog.deleteEvent(events.get(i)) == null, "EventCatalogImpl.deleteEvent() should actually remove the Event from the catalog!");
        }
    }

    @Test
    public void testDeleteTimeNullArgument() {
        try {
            catalog.deleteTime(null, times.get(0).get(0));
            fail("EventCatalogImpl.deleteTime() should throw a NullPointerException if the e argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            catalog.deleteTime(events.get(0), null);
            fail("EventCatalogImpl.deleteTime() should throw a NullPointerException if the t argument is " +
                    "null!");
        } catch (NullPointerException e) {
        }
    }

    // TODO: EventCatalog.getTimesOfEvent() should never return null, instead empty sets should be used
    @Test
    public void testDeleteTime() {
        if (catalog.deleteTime(newEvent, times.get(0).get(0))
                || catalog.deleteTime(events.get(0), times.get(1).get(0))) {
            fail("EventCatalogImpl.deleteTime() should return false if the catalog does not contain the "
                    + "given event/time pair");
        }

        for (int i = 0; i < events.size(); i++) {
            var event = events.get(i);
            var eventTimes = times.get(i);
            var iter = eventTimes.iterator();
            while (iter.hasNext()) {
                var time = iter.next();
                if (!catalog.deleteTime(event, time)) {
                    fail("EventCatalogImpl.deleteTime() should return true if the catalog contained the "
                            + "given event/time pair.");
                }
                Set<Time> actualTimes = catalog.getTimesOfEvent(event);
                if (actualTimes == null) {
                    fail("Unless an event has been deleted with EventCatalogImpl.deleteEvent(), "
                            + "EventCatalogImpl.getTimesOfEvent() must not return null.");
                }
                if (!iter.hasNext()) { // no more event times left to remove
                    assertThat(actualTimes) //
                        .as("After all times of an event have been deleted, EventCatalogImpl.getTimesOfEvent() "
                                + "should return an empty set.") //
                        .isEmpty();
                } else {
                    assertThat(actualTimes) //
                            .as("EventCatalogImpl.deleteTime() should actually delete the time from the set associated "
                                    + "to that event (as returned by EventCatalogImpl.getTimesOfEvent()).") //
                            .doesNotContain(time);
                }
                if (catalog.deleteTime(event, time)) {
                    fail("EventCatalogImpl.deleteTime() should return false if the catalog does not contain the given "
                            + "event/time pair (e.g., when it has already been deleted by an earlier call).");
                }
            }
        }
    }
}
