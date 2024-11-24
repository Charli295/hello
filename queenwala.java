import java.util.*;

public class queenwala {
    static int count = 0;
 
    public static boolean isSafe(char[][] board, int row, int col) {
       // vertical up
       for(int i = row - 1; i >= 0; i--) {
          if (board[i][col] == 'Q') {
             return false;
          }
       }
 
       // diagonal left up
       int i = row - 1;
       for(int j = col - 1; i >= 0 && j >= 0;j--,i--) {
          if (board[i][j] == 'Q') {
             return false;
          }
       }
 
       // diagonal right up
       i = row - 1;
       for(int j = col + 1; i >= 0 && j < board.length;j++,i--) {
          if (board[i][j] == 'Q') {
             return false;
          }
       }
 
       return true;
    }
 
    public static void nQueen(char[][] board, int row) {
      //base case
       if (row == board.length) {
          printBoard(board);
         count++;
       } 
        
       //column loop
          for(int col = 0; col < board.length; col++) {
             if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                nQueen(board, row + 1); //fun call
                board[row][col] = 'x';   //backtrack step
             }
          }
       
    }
 
    public static void printBoard(char[][] board) {
       System.out.println("--- Chess Board ---");
       for(int i = 0; i < board.length; i++) {
          for(int j = 0; j < board.length; j++) {
             System.out.print(board[i][j] + " ");
          }
          System.out.println();
       }
    }
 
    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      System.out.print("Enter a size of n : ");
       int n =sc.nextInt();
       
       char[][] board = new char[n][n];


       //intialize
       for(int i = 0; i < board.length; ++i) {
          for(int j = 0; j < board.length; ++j) {
             board[i][j] = 'x';
          }
       }
 
       nQueen(board, 0);
       System.out.println("Total solutions: " + count);
    }
 }


 //   o(n!)
 