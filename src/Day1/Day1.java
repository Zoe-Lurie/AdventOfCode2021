package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2021\\src\\Day1\\Input1"));
        List<Integer> nums = new ArrayList<>();

        nums.add(scanner.nextInt());
        nums.add(scanner.nextInt());
        nums.add(scanner.nextInt());
        int total = 0;
        while(scanner.hasNextInt()){
            nums.add(scanner.nextInt());
            int l = nums.size();
            if(nums.get(l-1) + nums.get(l-2) + nums.get(l-3) > nums.get(l-2) + nums.get(l-3) + nums.get(l-4)){
                total++;
            }
        }

        System.out.println(total);

    }
}
