import java.util.stream.*;

class Solution {
    private int[] useArr(int n, int k) {
        int[] answer = new int[n/k];
        for(int i=0; i<answer.length; i++) {
            answer[i] = k * (i+1);
        }
        return answer;
    }
    
    private int[] useStream(int n, int k) {
        return IntStream.rangeClosed(1, n).filter(num -> num % k == 0).toArray();
    }
    
    public int[] solution(int n, int k) {
        return useArr(n, k);
    }
}