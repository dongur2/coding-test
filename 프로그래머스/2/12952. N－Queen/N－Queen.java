import java.util.*;

//퀸 n개가 조건에 만족할 수 있는 배치 수
// <퀸이 서로 공격할 수 없는 배치>
class Solution {
    //가로,세로,대각선
    int[] dRow = {0, 0, -1, -1, -1};
    int[] dCol = {1, -1, -1, 0, 1};
    
    int answer = 0;
    
    public int solution(int n) {
        int[][] table = new int[n][n];
        
        //1행당 퀸1 - 윗행에 있는 퀸들의 공격 범위에 있는지 확인 후 배치
        placeQueen(table, n, 0);
        return answer;
    }
 
    void placeQueen(int[][] table, int n, int r) {
        //퀸 모두 배치했으면 카운트 후 종료
        if(r >= n) {
            answer++;
            return;
        }
        
        for(int c=0; c<n; c++) {
            //공격범위 포함 확인
            if(isSafeArea(table, n, r, c)) {
                table[r][c] = 1; //배치
                placeQueen(table, n, r+1);
                table[r][c] = 0; //회수
            }
        }
    }
    
    boolean isSafeArea(int[][] table, int n, int r, int c) {
        for(int i=0; i<5; i++) {
            int nxtR = r + dRow[i];
            int nxtC = c + dCol[i];

            while(isValid(n, nxtR, nxtC)) {
                if(table[nxtR][nxtC] == 1) return false; //공격범위에 퀸이 있으면 중지
                nxtR += dRow[i];
                nxtC += dCol[i];
            }
        }
        return true;
    }
    
    boolean isValid(int n, int row, int col) {
        return row > -1 && row < n && col > -1 && col < n;
    }
}