import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void testDay1() {
        Day day = new Day1();
        assertEquals(55971, day.task1(false));
        assertEquals(54719, day.task2(false));
    }

    @Test
    public void testDay2() {
        Day day = new Day2();
        assertEquals(1931, day.task1(false));
        assertEquals(83105, day.task2(false));
    }

    @Test
    public void testDay4() {
        Day day = new Day4();
        assertEquals(20407, day.task1(false));
        assertEquals(23806951, day.task2(false));
    }

    @Test
    public void testDay5() {
        Day day = new Day5();
        assertEquals(107430936, day.task1(false));
        assertEquals(23738616, day.task2(false));
    }

    @Test
    public void testDay5v2() throws Exception {
        Day5v2 day = new Day5v2();
        assertEquals(107430936, day.task1());
        assertEquals(23738616, day.task2());
    }

    @Test
    public void testDay6() {
        Day day = new Day6();
        assertEquals(288, day.task1(true));
        assertEquals(138915, day.task1(false));
        assertEquals(71503, day.task2(true));
        assertEquals(27340847, day.task2(false));
    }
}
