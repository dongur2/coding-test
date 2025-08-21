/*
    m x n 보드
    로봇은 아래쪽, 오른쪽 1칸씩 이동 
    >>> [0][0] -> [m-1][n-1]까지의 경로 수 
 */
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        //row[0],col[0]은 무조건 1
        for(int i=0; i<n; i++) {
            dp[0][i] = 1;
        }

        for(int i=0; i<m; i++) {
            dp[i][0] = 1;
        }

        for(int r=1; r<m; r++) {
            for(int c=1; c<n; c++) {
                //왼쪽+위쪽 (맨 첫번째 열과 행을 제외하고는 왼쪽에서 오는 경로, 윗쪽에서 오는 경로를 합하면 그 칸까지 오는 총 경로 수)
                dp[r][c] = dp[r-1][c] + dp[r][c-1];
            }
        }

        return dp[m-1][n-1];
    }
}