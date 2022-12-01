import org.franket.aoc2022.Day1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdventOfCode2022Test {

    @Test
    void day1_part1() {
        var day1 = new Day1();
        var result = day1.solvePart1("day1input_example.txt");

        assertEquals(result, 24000);

        System.out.println(day1.solvePart1("day1input.txt"));
    }

    @Test
    void day1_part2() {
        var day1 = new Day1();
        var result = day1.solvePart2("day1input_example.txt");

        assertEquals(result, 45000);

        System.out.println(day1.solvePart2("day1input.txt"));
    }
}
