import java.util.stream.*;

class Solution {
    public int solution(int n) {
        int[] cnt = new int[n+1]; // 인덱스의 약수 개수
        
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n/i; j++) {
                cnt[i*j]++;
            }
        }
        
        // int answer = 0;
        // for(int c:cnt) {
        //     if(c==2) answer++;
        // }
        // return answer;
        return (int)IntStream.of(cnt).filter(c -> c==2).count();
        
    }
}