import org.franket.aoc2021.day1.Day1AoC2021;
import org.franket.aoc2021.day2.Day2AoC2021;
import org.junit.jupiter.api.Test;

class AdventOfCode2021Test {

    @Test
    void day1() {
        var result = Day1AoC2021.part1("2021/day1input.txt");

        System.out.println(result);

        var part2 = Day1AoC2021.part2("2021/day1input.txt");

        System.out.println(part2);
    }

    @Test
    void day2() {
        var result = Day2AoC2021.part1("2021/day2input.txt");

        System.out.println(result);

        var part2 = Day2AoC2021.part2("2021/day2input.txt");

        System.out.println(part2);
    }
}
