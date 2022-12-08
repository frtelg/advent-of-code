import org.franket.aoc2022.day1.Day1;
import org.franket.aoc2022.day2.Day2;
import org.franket.aoc2022.day3.Day3;
import org.franket.aoc2022.day4.Day4;
import org.franket.aoc2022.day5.Day5;
import org.franket.aoc2022.day6.Day6;
import org.franket.aoc2022.day7.Day7;
import org.franket.aoc2022.day8.Day8;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdventOfCode2022Test {

    @Test
    void day1_part1() {
        var day1Example = new Day1("day1input_example.txt");
        var result = day1Example.solvePart1();

        assertEquals(24000, result);

        var day1 = new Day1("day1input.txt");
        System.out.println(day1.solvePart1());
    }

    @Test
    void day1_part2() {
        var day1Example = new Day1("day1input_example.txt");
        var result = day1Example.solvePart2();

        assertEquals(45000, result);

        var day1 = new Day1("day1input.txt");
        System.out.println(day1.solvePart2());
    }

    @Test
    void day2_part1() {
        var day2Example = new Day2("day2input_example.txt");
        var result = day2Example.solvePart1();

        assertEquals(15, result);

        var day2 = new Day2("day2input.txt");
        System.out.println(day2.solvePart1());
    }

    @Test
    void day2_part2() {
        var day2Example = new Day2("day2input_example.txt");
        var result = day2Example.solvePart2();

        assertEquals(12, result);

        var day2 = new Day2("day2input.txt");
        System.out.println(day2.solvePart2());
    }

    @Test
    void day3_part1() {
        var day3Example = new Day3("day3input_example.txt");
        var result = day3Example.solvePart1();

        assertEquals(157, result);

        var day3 = new Day3("day3input.txt");
        System.out.println(day3.solvePart1());
    }

    @Test
    void day3_part2() {
        var day3Example = new Day3("day3input_example.txt");
        var result = day3Example.solvePart2();

        assertEquals(70, result);

        var day3 = new Day3("day3input.txt");
        System.out.println(day3.solvePart2());
    }

    @Test
    void day4_part1() {
        var day4Example = new Day4("day4input_example.txt");
        var result = day4Example.solvePart1();

        assertEquals(2, result);

        var day4 = new Day4("day4input.txt");
        System.out.println(day4.solvePart1());
    }

    @Test
    void day4_part2() {
        var day4Example = new Day4("day4input_example.txt");
        var result = day4Example.solvePart2();

        assertEquals(4, result);

        var day4 = new Day4("day4input.txt");
        System.out.println(day4.solvePart2());
    }

    @Test
    void day5_part1() {
        var day5Example = new Day5("day5input_example.txt");
        var result = day5Example.solvePart1();

        assertEquals("CMZ", result);

        var day5 = new Day5("day5input.txt");
        System.out.println(day5.solvePart1());
    }

    @Test
    void day5_part2() {
        var day5Example = new Day5("day5input_example.txt");
        var result = day5Example.solvePart2();

        assertEquals("MCD", result);

        var day5 = new Day5("day5input.txt");
        System.out.println(day5.solvePart2());
    }

    @Test
    void day6_part1() {
        var day6Example1 = new Day6("day6input_example1.txt");
        var result1 = day6Example1.solvePart1();

        assertEquals(5, result1);

        var day6Example2 = new Day6("day6input_example2.txt");
        var result2 = day6Example2.solvePart1();

        assertEquals(6, result2);

        var day6Example3 = new Day6("day6input_example3.txt");
        var result3 = day6Example3.solvePart1();

        assertEquals(10, result3);

        var day6Example4 = new Day6("day6input_example4.txt");
        var result4 = day6Example4.solvePart1();

        assertEquals(11, result4);

        var day6 = new Day6("day6input.txt");
        System.out.println(day6.solvePart1());
    }

    @Test
    void day6_part2() {

        var input = Map.of(
                "day6input_example5.txt", 19,
                "day6input_example6.txt", 23,
                "day6input_example7.txt", 23,
                "day6input_example8.txt", 29,
                "day6input_example9.txt", 26
        );

        input.forEach((k, v) -> {
            var day6Example = new Day6(k);
            assertEquals(v, day6Example.solvePart2());
        });

        var day6 = new Day6("day6input.txt");

        System.out.println(day6.solvePart2());
    }

    @Test
    void day7_part1() {
        var day7Example = new Day7("day7input_example.txt");
        var result = day7Example.solvePart1();

        assertEquals(95437, result);

        var day7 = new Day7("day7input.txt");
        System.out.println(day7.solvePart1());
    }

    @Test
    void day7_part2() {
        var day7Example = new Day7("day7input_example.txt");
        var result = day7Example.solvePart2();

        assertEquals(24933642, result);

        var day7 = new Day7("day7input.txt");
        System.out.println(day7.solvePart2());
    }

    @Test
    void day8_part1() {
        var day8Example = new Day8("day8input_example.txt");
        var result = day8Example.solvePart1();

        assertEquals(21, result);

        var day8 = new Day8("day8input.txt");
        System.out.println(day8.solvePart1());
    }

    @Test
    void day8_part2() {
        var day8Example = new Day8("day8input_example.txt");
        var result = day8Example.solvePart2();

        assertEquals(8, result);

        var day8 = new Day8("day8input.txt");
        System.out.println(day8.solvePart2());
    }

}
