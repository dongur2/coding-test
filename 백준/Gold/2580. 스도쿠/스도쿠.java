import java.util.*;
import java.io.*;

//스도쿠 숫자를 채운 결과 출력 
public class Main {
  public static void main(String[] args) throws IOException{
    //가로 행에 1-9까지 중복 기입 불가
    //세로 열에 1-9까지 중복 기입 불가 
    //3*3 그리드 안에 1-9까지 중복 기입 불가 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //스도쿠 보드 생성
    int[][] board = new int[9][9];
    StringTokenizer st = null;
    for(int r=0; r<9; r++) {
      st = new StringTokenizer(br.readLine());  
      for(int c=0; c<9; c++) {
        int num = Integer.parseInt(st.nextToken());
        board[r][c] = num;
      }
    }
    
    completeSudoku(board);
    
    for(int r=0; r<9; r++) { 
      for(int c=0; c<9; c++) {
        System.out.print(board[r][c] +" ");
      }
      System.out.println();
    }
  }
  
  public static boolean completeSudoku(int[][] board) {
    //주어진 스도쿠에서 빈 칸을 탐색 (0)
    for(int r=0; r<9; r++) {
      for(int c=0; c<9; c++) {
        //빈 칸을 찾았을 경우 숫자 하나씩 넣어보기
        //숫자 하나를 넣고, 그 자리에서 또 스도쿠 빈 칸 탐색 
        if(board[r][c] == 0) {
          for(int num=1; num<=9; num++) {
            //넣어본 숫자가 규칙을 위반하지 않으면 진행
            if(!isValid(board, r, c, num)) continue;
            
            board[r][c] = num;
            if(completeSudoku(board)) return true; //마저 스도쿠를 다 채웠으면 반복 중지 
            board[r][c] = 0; //스도쿠를 더 못채웠으면 복구하고 다른 숫자 탐색 
          }
          //모든 숫자를 넣어봤는데 모두 위반이면 실패 
          return false;
        } 
      }
    }
    return true;
  }
  
  public static boolean isValid(int[][] board, int r, int c, int num) {
    //가로, 세로 확인 
    for(int i=0; i<9; i++) {
      if(board[r][i] == num && i != c) return false;
      if(board[i][c] == num && i != r) return false;
    }
    
    //그리드 영역 내 확인 
    //(0-2,0-2)(0-2,3-5)(0-2,6-8)
    //(3-5,0-2)(3-5,3-5)(3-5,6-8)
    //(6-8,0-2)(6-8,3-5)(6-8,6-8)
    int row = r == 0 ? r : (r / 3)*3; //0,0,0 1,1,1 2,2,2
    int col = c == 0 ? c : (c / 3)*3;
    for(int gr=row; gr<row+3; gr++) {
      for(int gc=col; gc<col+3; gc++) {
        if(board[gr][gc] == num) return false;
      }
    }
    return true;
  }
}