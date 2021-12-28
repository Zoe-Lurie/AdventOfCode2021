package Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(args[0]));
        List<String> incompleteLines = corruptedScore(scanner);
        System.out.println(calculateIncomplete(incompleteLines));
    }

    public static List<String> corruptedScore(Scanner scanner){
        int total = 0;
        List<String> incomplete = new ArrayList<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            StringBuilder count = new StringBuilder();
            boolean corrupt = false;
            for(int i=0; i<line.length(); i++){
                char c = line.charAt(i);
                if(c == '(' || c == '[' || c == '{' || c == '<'){
                    count.append(c);
                }
                else{
                    switch(c){
                        case ')' -> {
                            if(count.charAt(count.length()-1) == '('){
                                count.deleteCharAt(count.length()-1);
                            }
                            else{
                                total += 3;
                                corrupt = true;
                            }
                        }
                        case ']' -> {
                            if(count.charAt(count.length()-1) == '['){
                                count.deleteCharAt(count.length()-1);
                            }
                            else{
                                total += 57;
                                corrupt = true;
                            }
                        }
                        case '}' -> {
                            if(count.charAt(count.length()-1) == '{'){
                                count.deleteCharAt(count.length()-1);
                            }
                            else{
                                total += 1197;
                                corrupt = true;
                            }
                        }
                        case '>' -> {
                            if(count.charAt(count.length()-1) == '<'){
                                count.deleteCharAt(count.length()-1);
                            }
                            else{
                                total += 25137;
                                corrupt = true;
                            }
                        }
                    }
                }
                if(corrupt){
                    break;
                }
            }
            if(!corrupt) incomplete.add(count.toString());
        }
        return incomplete;
    }

    public static long calculateIncomplete(List<String> incomplete){
        List<Long> scores = new ArrayList<>();
        for(String line : incomplete){
            long score = 0;
            for(int i=line.length()-1; i>=0; i--){
                score *= 5;
                switch(line.charAt(i)){
                    case '(' -> score += 1;
                    case '[' -> score += 2;
                    case '{' -> score += 3;
                    case '<' -> score += 4;
                }
            }
            scores.add(score);
        }
        Collections.sort(scores);
        return scores.get(scores.size()/2);
    }
}
