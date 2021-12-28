package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(args[0]));
        System.out.println(countPart2(scanner));
    }

    public static int countPart1(Scanner scanner){
        int total = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String outputValues = line.substring(line.indexOf("|")+2);
            String[] output = outputValues.split(" ");
            for(String value : output){
                int length = value.length();
                if(length == 2 || length == 4 || length == 3 || length == 7){
                    total ++;
                }
            }
        }
        return total;
    }

    public static int countPart2(Scanner scanner){
        int total = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] input = line.substring(0, line.indexOf("|")-1).split(" ");
            String[] output = line.substring(line.indexOf("|")+2).split(" ");
            String values = determineInput(input);
            total += evaluate(output, values);
        }

        return total;
    }

    public static String determineInput(String[] input){
        Arrays.sort(input, Comparator.comparingInt(String::length));
        List<List<Character>> possibilities = new ArrayList<>();
        for(int i=0; i<7; i++){
            possibilities.add(new ArrayList<>(Arrays.asList('a','b','c','d','e','f','g')));
        }

        int[] one = Number.getPieces(Number.ONE);
        modifyList(possibilities, one, input[0]);
        int[] seven = Number.getPieces(Number.SEVEN);
        modifyList(possibilities, seven, input[1]);
        int[] four = Number.getPieces(Number.FOUR);
        modifyList(possibilities, four, input[2]);

        int indexOfTwo = -1;
        int indexOfThree = -1;
        for(int i=3; i<6; i++){
            List<Character> chars = charList(input[i]);
            if(chars.containsAll(possibilities.get(3))){
                indexOfTwo = i;
            }
            else if(chars.containsAll(possibilities.get(1))){
                indexOfThree = i;
            }
        }
        int[] two = Number.getPieces(Number.TWO);
        modifyList(possibilities, two, input[indexOfTwo]);
        int[] three = Number.getPieces(Number.THREE);
        modifyList(possibilities, three, input[indexOfThree]);

        StringBuilder s = new StringBuilder();
        for(List<Character> list : possibilities){
            s.append(list.get(0));
        }
        return s.toString();
    }

    public static List<Character> charList(String s){
        List<Character> chars = new ArrayList<>();
        for(int i=0; i<s.length(); i++){
            chars.add(s.charAt(i));
        }
        return chars;
    }

    public static void modifyList(List<List<Character>> possibilities, int[] number, String input){
        List<Integer> list = new ArrayList<>();
        for(int num : number){
            list.add(num);
        }
        List<Character> chars = charList(input);

        for(int i=0; i<7; i++){
            if(!list.contains(i)){
                possibilities.get(i).removeAll(chars);
            }
            else{
                possibilities.get(i).retainAll(chars);
            }
        }
    }

    public enum Number{
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE;

        public static int[] getPieces(Number number){
            switch(number){
                case ZERO -> {
                    return new int[]{0,1,2,3,4,5};
                }
                case ONE -> {
                    return new int[]{1,2};
                }
                case TWO -> {
                    return new int[]{0,1,3,4,6};
                }
                case THREE -> {
                    return new int[]{0,1,2,3,6};
                }
                case FOUR -> {
                    return new int[]{1,2,5,6};
                }
                case FIVE -> {
                    return new int[]{0,2,3,5,6};
                }
                case SIX -> {
                    return new int[]{0,2,3,4,5,6};
                }
                case SEVEN -> {
                    return new int[]{0,1,2};
                }
                case EIGHT -> {
                    return new int[]{0,1,2,3,4,5,6};
                }
                case NINE -> {
                    return new int[]{0,1,2,3,5,6};
                }
            }
            return null;
        }

        public static int getNum(Number number){
            int num = 0;
            switch(number){
                case ONE -> num = 1;
                case TWO -> num = 2;
                case THREE -> num = 3;
                case FOUR -> num = 4;
                case FIVE -> num = 5;
                case SIX -> num = 6;
                case SEVEN -> num = 7;
                case EIGHT -> num = 8;
                case NINE -> num = 9;
            }
            return num;
        }
    }

    public static int evaluate(String[] output, String values){
        int total = 0;
        for(int i=output.length-1; i>=0; i--){
            List<Integer> segments = new ArrayList<>();
            for(int j=0; j<output[i].length(); j++){
                segments.add(values.indexOf(output[i].charAt(j)));
            }
            Collections.sort(segments);
            for(Number number : Number.values()){
                if(checkEquality(segments, Number.getPieces(number))){
                    total += (Number.getNum(number) * Math.pow(10, 3-i));
                    break;
                }
            }
        }
        return total;
    }

    public static boolean checkEquality(List<Integer> segments, int[] values){
        if(segments.size() != values.length) return false;
        for(int i=0; i<values.length; i++){
            if(segments.get(i) != values[i]) return false;
        }
        return true;
    }
}
