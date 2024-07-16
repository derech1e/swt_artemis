import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class ClockTest {
    private Clock clock;
    private MockObserver mock;

    @BeforeEach
    public void setUp() {
        clock = new Clock(10);
        mock = new MockObserver();
    }

    @Test
    public void testInitialValue() {
        Assertions.assertEquals(0, clock.getCurrentTime(), "Clock.currentTime should be initialized with the value 0!");
    }

    @Test
    public void testEndOfTimeIllegalArgument() {
        try {
            new Clock(-1);
            Assertions.fail("new Clock(…) should throw an IllegalArgumentException if the argument is negative!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testInitEndOfTime() {
        clock.run();
        Assertions.assertEquals(10, clock.getCurrentTime(), "new Clock(…) should set endOfTime correctly!");
    }

    @Test
    public void testRun() {
        MockObserver anotherMock = new MockObserver();
        clock.addObserver(mock);
        clock.addObserver(anotherMock);
        clock.run();
        List<Integer> expectedArgs = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        String message = "Clock.run() should notify each observer once for every second from 1 to endOfTime!";
        Assertions.assertEquals(expectedArgs, mock.getArguments(), message);
        Assertions.assertEquals(expectedArgs, anotherMock.getArguments(), message);
    }

    @Test
    public void testRunTwice() {
        clock.addObserver(mock);
        clock.run();
        clock.run();
        Assertions.assertEquals(20, mock.getNumOfCalls(), "Clock.run() should also work when used more than once!");
        Assertions.assertEquals(10, clock.getCurrentTime(), "Clock.run() should start with currentTime set to zero!");
    }

    @Test
    public void testRemoveObserver() {
        clock.addObserver(mock);
        clock.removeObserver(mock);
        clock.run();
        Assertions.assertEquals(0, mock.getNumOfCalls(), "Removed observers should not be notified during Clock.run()!");
    }

    /**
     * Mock implementation for testing purposes. In practice, you would use a library such as Mockito.
     */
    private static class MockObserver implements ClockObserver {
        private List<Integer> ticks = new ArrayList<>();

        @Override
        public void tick(int currentTime) {
            ticks.add(currentTime);
        }

        public int getNumOfCalls() {
            return ticks.size();
        }

        public List<Integer> getArguments() {
            return List.copyOf(ticks);
        }
    }
}
