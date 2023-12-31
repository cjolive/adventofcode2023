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
    public void testDay3() {
        Day day = new Day3();
        assertEquals(4361, day.task1(true));
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
        // assertEquals(23738616, day.task2(false)); // slow
    }

    @Test
    public void testDay6() {
        Day day = new Day6();
        assertEquals(288, day.task1(true));
        assertEquals(138915, day.task1(false));
        assertEquals(71503, day.task2(true));
        assertEquals(27340847, day.task2(false));
    }

    @Test
    public void testDay7() {
        Day day = new Day7();
        assertEquals(6440, day.task1(true));
        assertEquals(251545216, day.task1(false));
        assertEquals(5905, day.task2(true));
        assertEquals(250384185, day.task2(false));
    }

    @Test
    public void testDay8() {
        Day day = new Day8();
        assertEquals(16579, day.task1(false));
        assertEquals(12927600769609L, day.task2(false));
    }
}
