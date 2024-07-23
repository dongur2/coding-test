import java.util.*;

class Solution {
    boolean[][] board;
    
    public int solution(int n) {
        //1. 체크판 생성
        board = new boolean[n][n];    
        
        return placeQueen(n, 0); //0행부터 시작
    }
    
    /* 퀸 놓기 */
    int placeQueen(int n, int row) {
        //base case: 행이 N과 같아지면 1을 리턴하고 종료(퀸 N개 위치 완료를 의미하므로)
        if(row == n) return 1;
        
        //recursive call: 고정된 행에서 열을 우측으로 하나씩 이동하며 위치를 탐색하고, 공격받지 않는 위치라면 퀸을 놓음
        int cnt = 0;
        for(int col=0; col<n; col++) {
            if(isSafePlace(row, col, n)) {
                board[row][col] = true; //퀸 놓기
                cnt += placeQueen(n, row+1); //다음 행으로 넘어가 위치 탐색 & 퀸 위치 반복
                board[row][col] = false; //이후 메서드(하청)에서 현재 자리에 퀸을 놓았을 때 유효한 경우의 수를 카운트하고 돌아왔을 것이므로 퀸 회수
            } 
        }
        return cnt;
    }
    
    /* 다른 퀸이 공격하지 않을 자리를 탐색 */
    boolean isSafePlace(int row, int col, int n) {
        //1. 같은 열에 퀸이 있는지 탐색: 매개변수로 넘어온 열을 고정하고 행을 모두 탐색(정사각형)
        for(int i=0; i<row; i++) {
            if(board[i][col]) return false;
        }
        
        /* 
        *   최대로 갈 수 있는 행이 row니까 언제나 현재 행보다 윗 행부터 탐색을 시작함 
        */
        
        //2. 오른쪽->왼쪽으로 올라가는 대각선에 퀸이 있는지 탐색(이 대각선은 'row-column 값이 모두 같음'을 이용)
        //   현재 위치에서 바로 왼쪽 대각선 위부터 시작: 북서쪽으로 이동하면서 왼쪽 대각선 위 라인에 퀸이 위치하고 있는지 확인
        for(int i=row-1, j=col-1; i>=0 && j>=0; i--,j--) {
            if(board[i][j]) return false;
        }
        
        //3. 왼쪽->오른쪽으로 올라가는 대각선에 퀸이 있는지 탐색(이 대각선은 'row+column 값이 모두 같음'을 이용)
        //   현재 위치에서 바로 오른쪽 대각선 위부터 시작: 북동쪽으로 이동하면서 오른쪽 대각선 위 라인에 퀸이 위치하고 있는지 확인
        for(int i=row-1, j=col+1; i>=0 && j<n; i--, j++) {
            if(board[i][j]) return false;
        }
        
        //4. 위 조건문에 걸리지 않았다면, 열/대각선에 퀸이 있지 않으므로 안전함
        return true; 
    }
}