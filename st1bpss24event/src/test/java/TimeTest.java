import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class TimeTest {
    private Time time;

    @BeforeEach
    public void setUp() {
        time = new Time(18, 30);
    }

    @Test
    public void timeTestConstructorIllegalArgument() {
        try {
            new Time(-1, 10);
            fail("Time.Time() should throw an IllegalArgumentException if the hour argument is invalid!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new Time(24, 10);
            fail("Time.Time() should throw an IllegalArgumentException if the hour argument is invalid!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new Time(20, -1);
            fail("Time.Time() should throw an IllegalArgumentException if the minute argument is invalid!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new Time(20, 60);
            fail("Time.Time() should throw an IllegalArgumentException if the minute argument is invalid!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void timeTestGetHour() {
        assertEquals(18, time.getHour(), "Time.getHour() should return the correct value!");
    }

    @Test
    public void timeTestGetMinute() {
        assertEquals(30, time.getMinute(), "Time.getMinute() should return the correct value!");
    }
}
