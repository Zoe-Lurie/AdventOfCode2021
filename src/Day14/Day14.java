package Day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day14 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(input(args[0]));
    }

    public static long input(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        String current = scanner.nextLine();
        scanner.nextLine();
        Map<String, String> instructions = new HashMap<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            instructions.put(line.substring(0,2), line.substring(6,7));
        }

        for(int i=0; i<5; i++){
            System.out.println(i + ": " + current);
            current = performStep(current, instructions);
        }

        Map<Character, Long> count = new HashMap<>();
        for(int i=0; i<current.length(); i++){
            char c = current.charAt(i);
            if(count.containsKey(c)){
                count.put(c, count.get(c)+1);
            }
            else{
                count.put(c, 1L);
            }
        }
        long large = 0;
        long small = Integer.MAX_VALUE;
        for(Long num : count.values()){
            if(num > large) large = num;
            if (num < small) small = num;
        }
        return large - small;
    }

    public static String performStep(String current, Map<String, String> instructions){
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<current.length()-1; i++){
            builder.append(current.charAt(i));
            if(instructions.containsKey(current.substring(i, i+2))){
                builder.append(instructions.get(current.substring(i, i+2)));
            }
        }
        builder.append(current.charAt(current.length()-1));
        return builder.toString();
    }
}
