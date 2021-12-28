package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];
        Scanner scanner = new Scanner(new File(filename));
        System.out.println(part1(scanner));
    }

    public static int part1(Scanner scanner){
        Map<Vent, Integer> ventMap = new HashMap<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            int x1 = Integer.parseInt(line.substring(0, line.indexOf(',')));
            int y1 = Integer.parseInt(line.substring(line.indexOf(',')+1, line.indexOf(' ')));
            int x2 = Integer.parseInt(line.substring(line.lastIndexOf(' ')+1, line.lastIndexOf(',')));
            int y2 = Integer.parseInt(line.substring(line.lastIndexOf(',')+1));
            if(x1 == x2){
                int large = y1;
                int small = y2;
                if(y2 > y1) {
                    large = y2;
                    small = y1;
                }
                for(int i=small; i<= large; i++){
                    Vent vent = new Vent(x1, i);
                    if(ventMap.containsKey(vent)){
                        ventMap.put(vent, 2);
                    }
                    else{
                        ventMap.put(vent, 1);
                    }
                }
            }
            else if(y1 == y2){
                int large = x1;
                int small = x2;
                if(x2 > x1){
                    large = x2;
                    small = x1;
                }
                for(int i=small; i<=large; i++){
                    Vent vent = new Vent(i, y1);
                    if(ventMap.containsKey(vent)){
                        ventMap.put(vent, 2);
                    }
                    else{
                        ventMap.put(vent, 1);
                    }
                }
            }
            else{
                int dif = Math.abs(x1-x2);
                int curX = x1;
                int curY = y1;
                if(x1 > x2) curX ++;
                else curX --;
                if(y1 > y2) curY ++;
                else curY --;
                for(int i=0; i<dif+1; i++){
                    if(x1 > x2) curX --;
                    else curX ++;
                    if(y1 > y2) curY --;
                    else curY ++;
                    Vent vent = new Vent(curX, curY);
                    if(ventMap.containsKey(vent)){
                        ventMap.put(vent, 2);
                    }
                    else{
                        ventMap.put(vent, 1);
                    }
                }
            }
        }
        int total = 0;
        for(int value : ventMap.values()){
            if(value == 2) total++;
        }
        return total;
    }

    private static class Vent{
        int[] ventCords;

        public Vent(int x, int y){
            ventCords = new int[]{x, y};
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vent vent = (Vent) o;
            return ventCords[0] == vent.ventCords[0] && ventCords[1] == vent.ventCords[1];
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(ventCords[0]) + Integer.hashCode(ventCords[1]);
        }
    }
}
