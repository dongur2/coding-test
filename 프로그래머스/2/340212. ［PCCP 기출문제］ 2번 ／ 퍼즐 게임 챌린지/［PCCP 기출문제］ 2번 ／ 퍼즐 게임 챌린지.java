import java.util.*;

/*
제한 시간 내 모든 퍼즐을 해결할 수 있는 '최소 숙련도'
- 퍼즐 순서 고정
- 숙련도 >= 난이도: 현재 퍼즐 소요 시간
- 숙련도 < 난이도: (난이도 - 숙련도)번 틀리고, 틀릴 때마다 +현재 퍼즐 소요 시간 +이전 퍼즐 소요 시간
*/
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        //최고 숙련도 = 퍼즐 중 최고 난이도
        int answer = Arrays.stream(diffs).max().getAsInt();
        
        //최소 숙련도 찾기: 중간값으로 좁혀가기
        int left = 1;
        int right = answer;
      
        //숙련도를 낮춰가면서, 제한 시간 내 모든 퍼즐 해결이 가능한지 확인
        while(left <= right) {
            int mid = (left + right) / 2;
            
            if(canSolve(diffs, times, limit, mid)) {
                answer = mid;
                right = mid - 1;
            } else left = mid + 1;
        }
        
        return answer;
    }
    
    boolean canSolve(int[] diffs, int[] times, long limit, int level) {
        long time = 0;
        for(int i=0; i<diffs.length; i++) {
            if(level >= diffs[i]) time += times[i];
            else {
                int miss = diffs[i] - level;
                time += (times[i-1] + times[i]) * miss + times[i];
            }
            if(time > limit) return false;
        }
        return time <= limit;
    }
}