package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2021\\src\\Day3\\Input3"));
        List<String> oxygen = new ArrayList<>();
        List<String> co2 = new ArrayList<>();

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            oxygen.add(line);
            co2.add(line);
        }

        int i=0;
        while(oxygen.size() > 1){
            int count = 0;
            for(String data : oxygen){
                if(data.charAt(i) == '1') count++;
            }
            char check = '0';
            if(count >= (double)oxygen.size()/2) check = '1';
            int finalI = i;
            char finalCheck = check;
            oxygen.removeIf(data -> data.charAt(finalI) != finalCheck);
            i++;
        }
        int j=0;
        while(co2.size() > 1){
            int count = 0;
            for(String data : co2){
                if(data.charAt(j) == '1') count++;
            }
            char check = '1';
            if(count >= (double)co2.size()/2) check = '0';
            int finalJ = j;
            char finalCheck = check;
            co2.removeIf(data -> data.charAt(finalJ) != finalCheck);
            j++;
        }

        System.out.println(oxygen.get(0));
        System.out.println(co2.get(0));
    }
}
