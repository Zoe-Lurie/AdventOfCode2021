package Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(args[0]));
        System.out.println(crabs(scanner));
    }

    public static int crabs(Scanner scanner){
        String[] stringCrabs = scanner.nextLine().split(",");
        int[] crabs = new int[stringCrabs.length];
        int large = Integer.parseInt(stringCrabs[0]);
        int small = large;
        for(int i=0; i<stringCrabs.length; i++){
            int crab = Integer.parseInt(stringCrabs[i]);
            crabs[i] = crab;
            if(crab > large) large = crab;
            if(crab < small) small = crab;
        }

        Map<Integer, Integer> consumption = new HashMap<>();
        int total = 0;
        for(int i=0; i<large-small+1; i++){
            total += i;
            consumption.put(i, total);
        }

        int leastFuel = Integer.MAX_VALUE;
        for(int i=small; i<large+1; i++){
            int fuel = 0;
            for(int crab : crabs){
                fuel += consumption.get(Math.abs(crab-i));
            }
            if(fuel < leastFuel) leastFuel = fuel;
        }
        return leastFuel;
    }
}
