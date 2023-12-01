import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void testDay1() {
        Day day = new Day1();
        assertEquals(55971, day.task1());
        assertEquals(54719, day.task2());
    }
}
