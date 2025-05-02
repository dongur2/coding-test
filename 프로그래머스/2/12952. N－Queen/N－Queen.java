//퀸이 서로 공격할 수 없는 배치 수
class Solution {
    static int answer = 0;
    static int[][] board;
    
    public int solution(int n) {
        board = new int[n][n]; //체스핀
        dfs(n, 0);
        return answer;    
    }
    
    public static void dfs(int n, int row) {
        //모든 퀸을 배치했으면 카운트 후 복귀
        if(row == n) {
            answer++;
            return;
        }
        
        //현재 행의 모든 열을 탐색
        for(int c=0; c<n; c++) {
            //상(하), 대각선 끝까지 퀸이 있는지 확인 - 행마다 하나씩 놓으니까 좌우는 어차피 없고, 아래는 아직 안갔으니 없음
            if(isValid(n, row, c)) {
                board[row][c] = 1;
                dfs(n, row+1);
                board[row][c] = 0; //복구
            }
        }
    }
    
    public static boolean isValid(int n, int r, int c) {
        for(int row=0; row<=r; row++) {
            //윗줄
            if(board[row][c] == 1) return false;
        } 
        
        //오른쪽 위 대각선
        for(int row = r, col = c; row >= 0 && col < n; row--, col++) {
            if(board[row][col] == 1) return false;
        }

        //왼쪽 위 대각선
        for(int row = r, col = c; row >= 0 && col >= 0; row--, col--) {
            if(board[row][col] == 1) return false;            
        }
        
        return true;
    }
}