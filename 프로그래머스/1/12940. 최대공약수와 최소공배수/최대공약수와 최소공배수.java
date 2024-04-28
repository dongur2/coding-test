class Solution {
    private int GCD(int n, int m) {
        if(m == 0) return n;
        return GCD(m, n % m);
    }
    
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        
        answer[0] = GCD(n,m);
        answer[1] = n*m / answer[0];        
        // for(int i=1; ;i++) {
        //     if(answer[0] * i % n == 0 && answer[0] * i % m == 0) {
        //         answer[1] = answer[0] * i;
        //         break;
        //     }
        // }
        
        return answer;
    }

}