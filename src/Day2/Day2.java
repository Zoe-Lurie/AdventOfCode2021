package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2021\\src\\Day2\\Input2"));


        int depth = 0;
        int pos = 0;
        int real = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            int num = Integer.parseInt(line.substring(line.indexOf(' ')+1));
            if(line.startsWith("forward")){
                pos += num;
                real += depth * num;
            }
            else if(line.startsWith("down")){
                depth += num;
            }
            else{
                depth -= num;
            }
        }
        System.out.println(pos * real);
    }
}
