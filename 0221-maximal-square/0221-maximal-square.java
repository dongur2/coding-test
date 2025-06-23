//1로만 이루어진 가장 넓은 '정사각형' 영역의 넓이 리턴 
class Solution {
    static int m, n;

    public int maximalSquare(char[][] matrix) {
        m = matrix.length; n = matrix[0].length;

        //가능한 한 변의 최대 길이 저장 
        int[][] dp = new int[m+1][n+1];

        int max = 0; //최대값

        //1인 칸에서만 확인 - (2*2 구역 내 가장 작은값 + 1)이 가능한 한 변의 최대 길이가 된다. 
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }
}