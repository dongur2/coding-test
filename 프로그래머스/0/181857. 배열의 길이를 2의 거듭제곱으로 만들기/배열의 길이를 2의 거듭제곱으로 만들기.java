import java.util.*;

class Solution {
    private int[] my(int[] arr) {
       int lengthOfArr = arr.length;
        
        int t = 0;
        int length = 1;
        while(true) {
            length = (int)Math.pow(2, t++);
            if(length >= lengthOfArr) break;
        }
        
        int[] answer = new int[length];
        for(int i=0; i<lengthOfArr; i++) answer[i] = arr[i];
        
        return answer; 
    }
    
    private int[] useArrays(int[] arr) {
        int length = 1;

        while (length < arr.length) {
            length *= 2;
        }

        return Arrays.copyOf(arr, length);
    }
    
    public int[] solution(int[] arr) {
        return useArrays(arr);
    }
}