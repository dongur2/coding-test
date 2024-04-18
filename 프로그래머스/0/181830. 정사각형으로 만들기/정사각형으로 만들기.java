import java.util.*;

class Solution {
    public int[][] solution(int[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;

        int united = Math.max(rows, cols);
        
        int[][] answer = new int[united][united];
        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                answer[i][j] = arr[i][j];
            }
        }

        return answer;
    }
}