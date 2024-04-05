import java.util.*;

class Solution {
    public int[] solution(int n) {
        List<Integer> dividers = new ArrayList<>();
        
        // n의 약수
        for(int i=2; i<=n; i++) {
            if(n % i == 0) dividers.add(i);
        }
        
        // 소수일 경우 즉시 리턴
        if(dividers.size() == 1) return new int[] {n};

        // 소수가 아닐 경우, 약수 중 소수 필터링
        List<Integer> answerList = new ArrayList<>();
        
        for(int i=0; i<dividers.size(); i++) {
            int count = 0;
            
            for(int j=2; j<=dividers.get(i); j++) {
                if(dividers.get(i) % j == 0) {
                    count++;
                    if(count > 1) break;
                }
            }
            if(count == 1) answerList.add(dividers.get(i));
        }
        
        int[] answer = new int[answerList.size()];
        for(int i=0; i<answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}