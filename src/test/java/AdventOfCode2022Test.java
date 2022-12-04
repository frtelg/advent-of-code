import org.franket.aoc2022.day1.Day1;
import org.franket.aoc2022.day2.Day2;
import org.franket.aoc2022.day2.RockPaperScissorsChoice;
import org.franket.aoc2022.day3.Day3;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
    void test() {

        char a = 'a';
        char b = 'b';
        char aUc = 'A';

        System.out.println(Character.getNumericValue(a));
        System.out.println(Character.getNumericValue(aUc));

        System.out.println((int) a);
        System.out.println((int) b);
        System.out.println((int) aUc);

    }

}
