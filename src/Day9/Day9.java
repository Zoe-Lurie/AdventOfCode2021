package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(args[0]));
        int[][] grid = createGrid(scanner);
        List<int[]> lowPointList = countLowPoints(grid);
        List<Integer> basins = basinSizes(grid, lowPointList);
        System.out.println(finalAnswer(basins));
    }

    public static int[][] createGrid(Scanner scanner){
        int[][] grid = new int[100][100];
        for(int i=0; i<100; i++){
            String line = scanner.nextLine();
            for(int j=0; j<100; j++){
                grid[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }
        return grid;
    }

    public static List<int[]> countLowPoints(int[][] grid){
        int total = 0;
        List<int[]> list = new ArrayList<>();
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if(lowPoint(grid, j, i)){
                    total += (1+grid[i][j]);
                    int[] cords = new int[]{j,i};
                    list.add(cords);
                }
            }
        }
        return list;
    }

    public static boolean lowPoint(int[][] grid, int x, int y){
        int num = grid[y][x];
        if(x > 0 && grid[y][x-1] <= num) return false;
        if(x < 99 && grid[y][x+1] <= num) return false;
        if(y > 0 && grid[y-1][x] <= num) return false;
        if(y < 99 && grid[y+1][x] <= num) return false;
        return true;
    }

    public static List<Integer> basinSizes(int[][] grid, List<int[]> lowPoints){
        List<Integer> sizes = new ArrayList<>();
        for(int[] point : lowPoints){
            int size = 1;

            Set<Cords> visited = new HashSet<>();
            Queue<Cords> queue = new LinkedList<>();
            visited.add(new Cords(point[0], point[1]));
            queue.add(new Cords(point[0], point[1]));

            while(!queue.isEmpty()){
                Cords current = queue.remove();
                for(Cords neighbor : current.getNeighbors()){
                    if(!visited.contains(neighbor) && grid[neighbor.y][neighbor.x] != 9){
                        queue.add(neighbor);
                        visited.add(neighbor);
                        size ++;
                    }
                }
            }

            sizes.add(size);
        }

        return sizes;
    }

    public static class Cords{
        private final int x;
        private final int y;

        public Cords(int x, int y){
            this.x = x;
            this.y = y;
        }

        public List<Cords> getNeighbors(){
            List<Cords> neighbors = new ArrayList<>();
            if(x > 0) neighbors.add(new Cords(x-1, y));
            if(x < 99) neighbors.add(new Cords(x+1, y));
            if(y > 0) neighbors.add(new Cords(x, y-1));
            if(y < 99) neighbors.add(new Cords(x, y+1));
            return neighbors;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cords cords = (Cords) o;
            return x == cords.x && y == cords.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static long finalAnswer(List<Integer> basins){
        Collections.sort(basins);
        int size = basins.size();
        return (long)basins.get(size-1) * basins.get(size-2) * basins.get(size-3);
    }
}
