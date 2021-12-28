package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2021\\src\\Day4\\Input4"));
        String[] nums = scanner.nextLine().split(",");
        List<int[][]> boards = new ArrayList<>();
        while(scanner.hasNextInt()){
            int[][] board = new int[5][5];
            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    board[i][j] = scanner.nextInt();
                }
            }
            boards.add(board);
        }

        for(String num : nums){
            int realNum = Integer.parseInt(num);
            List<int[][]> toRemove = new ArrayList<>();
            for(int[][] board : boards){
                for(int i=0; i<5; i++){
                    for(int j=0; j<5; j++){
                        if(board[i][j] == realNum){
                            board[i][j] = -1;
                        }
                    }
                }
                if(checkWin(board)){
                    toRemove.add(board);
                }
            }
            for(int[][] board : toRemove){
                boards.remove(board);
                if(boards.size() == 0){
                    System.out.println(countWin(board) * realNum);
                }
            }
        }
    }

    public static boolean checkWin(int[][] board){
        for(int i=0; i<5; i++){
            boolean colWin = true;
            for(int j=0; j<5; j++){
                if(board[i][j] != -1){
                    colWin = false;
                    break;
                }
            }
            if(colWin) return true;
        }
        for(int j=0; j<5; j++){
            boolean rowWin = true;
            for(int i=0; i<5; i++){
                if(board[i][j] != -1){
                    rowWin = false;
                    break;
                }
            }
            if(rowWin) return true;
        }
        return false;
    }

    public static int countWin(int[][] board){
        int total = 0;
        for(int[] nums : board){
            for(int num : nums){
                if(num != -1) total += num;
            }
        }
        return total;
    }
}
