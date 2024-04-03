import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        Arrays.sort(numbers);
        
        int left = numbers[0] * numbers[1];
        int right = numbers[numbers.length-1] * numbers[numbers.length-2];
        
        return left > right? left:right;
    }
}