package Day12;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class Day12Test {
    @Test
    void test() throws FileNotFoundException {
        String filename = "C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2021\\src\\Day12\\Input12Test";
        assertEquals(3509, Day12.setup(filename));
    }
}
