import java.util.*;
import java.io.*;


//크기가 n*n인 체스판에서 퀸 n개가 서로 공격할 수 없는 배치 개수 
public class Main {
  static int answer = 0;
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int n = Integer.parseInt(br.readLine());
    int[][] board = new int[n][n];

    place(board, n, 0);
    
    System.out.println(answer);
  }
  
  public static void place(int[][] board, int n, int row) {
    //모든 퀸 배치 완료 
    if(row == n) {
      answer++; return;
    }
    
    for(int c=0; c<n; c++) {
      //빈 칸일 경우 유효한 자리인지 확인 후 배치 
      if(board[row][c] == 0 && isValid(board, n, row, c)) {
        board[row][c] = 1;
        place(board, n, row+1);
        board[row][c] = 0; //복구
      }
    }
  }
  
  public static boolean isValid(int[][] board, int n, int r, int c) {
    //윗 열에 퀸이 있는지 확인 
    for(int i=r; i>=0; i--) {
      if(board[i][c] == 1) return false;
    }
    
    //왼쪽 대각선 위에 퀸 있는지 확인 
    for(int i=1; r-i>=0 && c-i>=0; i++) {
      if(board[r-i][c-i] == 1) return false;
    }
    
    //오른쪽 대각선 위에 퀸 있는지 확인 
    for(int i=1; r-i>=0 && c+i<n; i++) {
      if(board[r-i][c+i] == 1) return false;
    }
    
    return true;
  }
}