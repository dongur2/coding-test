import java.util.*;

/*
모든 퍼즐을 해결하기 위한 최소 숙련도

- 제한 시간 내 n개 퍼즐을 순서대로 해결
- 퍼즐[난이도, 소요시간]
- 난이도 <= 숙련도면 소요시간만큼 사용
- 난이도 > 숙련도면 (난이도 - 숙련도)번 틀리고, 틀릴 때마다 소요시간, 그리고 이전 퍼즐 다시 풀고와야함 + 그 후에 소요시간
*/
class Solution {
    
    public int solution(int[] diffs, int[] times, long limit) {
        int[] temp = new int[diffs.length];
        for(int t=0; t<temp.length; t++) {
            temp[t] = diffs[t];
        }
        Arrays.sort(temp);
        
        int left = 1;
        int answer = temp[diffs.length-1]; //최대 난이도
        int right = answer;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            if(canSolve(diffs, times, mid, limit)) {
                answer = mid;
                right = mid - 1;
            } else left = mid + 1;
        }
        
        return answer;
    }
    
    public boolean canSolve(int[] diffs, int[] times, int level, long limit) {
        long time = 0L;
        
        //각 숙련도마다 퍼즐 풀어보기
        for(int idx=0; idx<diffs.length; idx++) {
            //숙련도 >= 난이도면 소요시간 사용
            if(level >= diffs[idx]) {
                time += times[idx];

            } else { //숙련도 < 난이도면
                //(난이도 - 숙련도)번 틀리고, 이전 퍼즐 풀이
                int missed = diffs[idx] - level;
                if(idx > 0) time += (times[idx] + times[idx-1]) * missed;
                else time += times[idx] * missed;

                //이후 소요시간으로 풀이 가능    
                time += times[idx];
            }
            if(time > limit) return false;
        } 
        return time <= limit;
    }
}