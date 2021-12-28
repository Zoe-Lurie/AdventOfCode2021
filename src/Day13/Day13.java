package Day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day13 {
    public static void main(String[] args) throws FileNotFoundException {
        setup(args[0]);
    }

    public static void  setup(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        Set<Point> pointSet = new HashSet<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.equals("")) break;
            int x = Integer.parseInt(line.substring(0, line.indexOf(',')));
            int y= Integer.parseInt(line.substring(line.indexOf(',')+1));
            Point point = new Point(x, y);
            pointSet.add(point);
        }
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            char xOrY = line.charAt(line.indexOf('=')-1);
            int foldNum = Integer.parseInt(line.substring(line.indexOf('=')+1));
            pointSet = fold(pointSet, xOrY, foldNum);
        }

        int largeX = 0;
        int largeY = 0;
        for(Point point : pointSet){
            if(point.x > largeX) largeX = point.x;
            if(point.y > largeY) largeY = point.y;
        }
        int[][] grid = new int[largeY+1][largeX+1];
        for(Point point : pointSet){
            grid[point.y][point.x] = 1;
        }
        for(int[] row : grid){
            for(int num : row){
                if(num == 0) System.out.print(' ');
                else System.out.print('0');
            }
            System.out.println();
        }
    }

    public static Set<Point> fold(Set<Point> pointSet, char xOrY, int foldNum){
        Set<Point> newSet = new HashSet<>();
        for(Point point : pointSet){
            Point newPoint;
            if(xOrY == 'x' && point.x > foldNum){
                newPoint = new Point(foldNum * 2 - point.x, point.y);
            }
            else if(xOrY == 'y' && point.y > foldNum){
                newPoint = new Point(point.x, foldNum * 2 - point.y);
            }
            else{
                newPoint = new Point(point.x, point.y);
            }
            newSet.add(newPoint);
        }
        return newSet;
    }

    public static class Point{
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
