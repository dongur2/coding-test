import java.util.*;

//제한 시간 내 퍼즐 해결 위한 최소 숙련도
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        //최대 숙련도: 주어진 난이도 중 최소 난이도 <= 최대 숙련도 <= 최고 난이도
        int answer = Arrays.stream(diffs).max().getAsInt();

        int left = 1;
        int right = answer;
        
        while(left <= right) {
            int mid = (left+right)/2;
            
            if(isSolved(diffs, times, limit, mid)) {
                answer = mid;
                right = mid - 1;
            }
            else left = mid + 1;
        }
        
        return answer;
    }
    
   boolean isSolved(int[] diffs, int[] times, long limit, int level) {
        long time = 0;

        for(int i=0; i<diffs.length; i++) {
            //난이도 <= 숙련도: 현재 퍼즐 소요 시간
            if(diffs[i] <= level) time += times[i];
            
            //난이도 > 숙련도: ((난이도 - 숙련도)번 실패 * (현재 퍼즐 소요 시간 + 이전 퍼즐 소요 시간)) + 현재 퍼즐 소요 시간
            else {
                int miss = diffs[i] - level;
                time += ((times[i-1]+times[i]) * miss) + times[i];
            }
            if(time > limit) return false;
        }

        return time <= limit;
    }
}