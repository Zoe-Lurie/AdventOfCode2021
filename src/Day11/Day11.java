package Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(args[0]));
        int[][] grid = input(scanner);
        System.out.println(simulate(grid));
    }

    public static int[][] input(Scanner scanner){
        int[][] grid = new int[10][10];
        for(int i=0; i<10; i++){
            String line = scanner.nextLine();
            for(int j=0; j<10; j++){
                grid[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }
        return grid;
    }

    public static int simulate(int[][] grid){
        int total = 0;
        for(int i=0; true; i++){
            for(int j=0; j<10; j++){
                for(int k=0; k<10; k++){
                    grid[j][k] ++;
                }
            }

            for(int j=0; j<10; j++){
                for(int k=0; k<10; k++){
                    if (grid[j][k] > 9) {
                        grid[j][k] = 0;
                        total ++;
                        total += flash(k, j, grid);
                    }
                }
            }
            int count = 0;
            for(int j=0; j<10; j++){
                for(int k=0; k<10; k++){
                    if(grid[j][k] == 0) count ++;
                }
            }
            if(count == 100) return i+1;
        }
    }

    public static int flash(int x, int y, int[][] grid){
        int total = 0;
        for(int i=-1; i<2; i++){
            for(int j=-1; j<2; j++){
                int newX = x+i;
                int newY = y+j;
                if(0 <= newX && newX < 10 && 0 <= newY && newY < 10 && (i!=0 || j!=0) && grid[newY][newX] != 0){
                    grid[newY][newX] ++;
                    if(grid[newY][newX] > 9){
                        grid[newY][newX] = 0;
                        total ++;
                        total += flash(newX, newY, grid);
                    }
                }
            }
        }
        return total;
    }
}
