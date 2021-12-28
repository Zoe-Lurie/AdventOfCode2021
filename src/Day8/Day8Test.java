package Day8;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static Day8.Day8.countPart2;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {

    @Test
    void countPart2Test() throws FileNotFoundException {
        assertEquals(61229, countPart2(new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2021\\src\\Day8\\Input8Test"))));
    }
}