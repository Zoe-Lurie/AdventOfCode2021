package Day11;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class Day11Test {
    @Test
    void test() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2021\\src\\Day11\\Input11Test"));
        int[][] grid = Day11.input(scanner);
        assertEquals(1656, Day11.simulate(grid));
    }
}
