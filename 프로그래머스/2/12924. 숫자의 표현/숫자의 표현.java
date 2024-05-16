class Solution {
    // 자연수 n => 연속되는 자연수로 표현되는 방법의 수 리턴
    public int solution(int n) {
        int cnt = 0;
        
        int sum = 0;
        for(int i=1; i<=n; i++) {
            sum = 0;
        
            for(int j=i; j<=n; j++) {
                sum += j;
                
                if(sum == n) {
                    cnt++; break;
                } else if (sum > n) break;
            }
        }
        return cnt;
    }
}