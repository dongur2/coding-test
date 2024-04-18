import java.util.*;

class Solution {
    private int[][] my(int[][] arr) {
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
    
    /*
    * System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
    * - copies an array from the specified source array
    * , beginning at the specified position, to the specified position of the destination array. 
    *
    * src - the source array.
    * srcPos - starting position in the source array.
    * dest - the destination array.
    * destPos - starting position in the destination data.
    * length - the number of array elements to be copied.
    */
    private int[][] useSystemCopy(int[][] arr) {
        int n = Math.max(arr.length, arr[0].length);    
        int[][] answer = new int[n][n];
        
        for(int i=0; i<arr.length; i++) {
            System.arraycopy(arr[i], 0, answer[i], 0, arr[i].length);
        }
        
        return answer;
    }
    
    public int[][] solution(int[][] arr) {
        return useSystemCopy(arr);
    }
}