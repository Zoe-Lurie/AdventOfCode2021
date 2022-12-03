package Day14;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day14Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/Day14/Input14"));
        String[] letters = {"P", "B", "S", "C", "F", "V", "H", "K", "N", "O"};
        Map<String, Integer> letnums= new HashMap<>();
        for(int i=0; i<letters.length; i++){
            letnums.put(letters[i], i);
        }

        String initial = scanner.nextLine();
        scanner.nextLine();
        Map<String, String> rules = new HashMap<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            rules.put(line.substring(0,2), line.substring(6,7));
        }

        String[][] array0 = new String[10][10];
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                array0[i][j] = letters[i] + rules.get(letters[i]+letters[j]) + letters[j];
            }
        }

        Count[][] carray1 = new Count[10][10];
        Count[][] carray2 = new Count[10][10];

        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                carray1[i][j] = new Count(letnums.get(letters[j]));
                carray1[i][j].counts[letnums.get(letters[i])] ++;
                carray1[i][j].counts[letnums.get(rules.get(letters[i] + letters[j]))] ++;
                carray1[i][j].counts[letnums.get(letters[j])] ++;
            }
        }

        for(int n=2; n<41; n++){
            for(int i=0; i<10; i++){
                for(int j=0; j<10; j++){
                    Count c1 = carray1[letnums.get(array0[i][j].substring(0, 1))][letnums.get(array0[i][j].substring(1, 2))];
                    Count c2 = carray1[letnums.get(array0[i][j].substring(1, 2))][letnums.get(array0[i][j].substring(2, 3))];
                    carray2[i][j] = new Count(c1, c2);
                }
            }
            for(int i=0; i<10; i++){
                for(int j=0; j<10; j++){
                    carray1[i][j] = new Count(carray2[i][j]);
                }
            }
        }

        Count total = carray1[letnums.get(initial.substring(0,1))][letnums.get(initial.substring(1,2))];
        for(int i=1; i<initial.length()-1; i++){
            Count c2 = carray1[letnums.get(initial.substring(i,i+1))][letnums.get(initial.substring(i+1,i+2))];
            total = new Count(total, c2);
        }

        System.out.println(total.getFinalAnswer());
    }
}
