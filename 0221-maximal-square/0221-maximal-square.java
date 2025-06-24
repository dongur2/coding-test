//주어진 맵에서 만들 수 있는 가장 큰 정사각형의 넓이
class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        //가장 긴 한 변의 길이 
        int max = 0;

        //해당 칸을 포함하는 가장 큰 정사각형의 한 변 길이 
        int[][] w = new int [m+1][n+1];

        //위, 왼쪽, 왼쪽위대각선 확인 
        //matrix[r-1][c-1] 위치의 '1'을 오른쪽 아래 끝점으로 삼는 가장 큰 정사각형의 한 변의 길이를 저장
        for(int r=1; r<=m; r++) {
            for(int c=1; c<=n; c++) {
                if(matrix[r-1][c-1] == '1') w[r][c] = Math.min(Math.min(w[r-1][c-1], w[r-1][c]), w[r][c-1]) + 1;
                max = Math.max(max, w[r][c]);
            }
        }

        return max * max;
    }
}