package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];
        Scanner scanner = new Scanner(new File(filename));
        long[] fish = input(scanner);
        System.out.println(simulate(fish));
    }

    public static long[] input(Scanner scanner){
        String[] fishes = scanner.nextLine().split(",");
        long[] numFish = new long[9];
        for(String fish : fishes){
            int daysLeft = Integer.parseInt(fish);
            numFish[daysLeft] ++;
        }
        return numFish;
    }

    public static long simulate(long[] fish){
        for(int i=0; i<256; i++){
            long[] temp = new long[9];
            System.arraycopy(fish, 1, temp, 0, 8);
            temp[8] = fish[0];
            temp[6] += fish[0];
            System.arraycopy(temp, 0, fish, 0, 9);
        }
        long total = 0;
        for(int i=0; i<9; i++){
            total += fish[i];
        }
        return total;
    }
}
