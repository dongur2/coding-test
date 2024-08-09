import java.util.*;

class Solution {
    //지원할 수 있는 최대 부서 개수 반환
    public int solution(int[] d, int budget) {
        int answer = 0;
        
        //정렬
        Arrays.sort(d);
        
        //앞부터 지원(신청 금액 적은 부서부터)
        for(int i=0; i<d.length; i++) {
            budget -= d[i];
            if(budget >= 0) answer++;
            else return answer;
        }
        
        return answer;
    }
}