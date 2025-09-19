import java.util.Arrays;
//제한 시간 내 퍼즐 모두 해결 가능한 최소 숙련도 
class Solution {
    static int answer = Integer.MAX_VALUE; 
    static int n;
    
    public int solution(int[] diffs, int[] times, long limit) {    
        n = diffs.length; //퍼즐 개수
        
        //내 숙련도: diffs목록의 최대 난이도와 최저 난이도(1) 사이 값을 확인 
        int right = Arrays.stream(diffs).max().getAsInt();
        int left = 1;
        
        while(right >= left) {
            int mid = (right + left) / 2;
            if(isValid(diffs, times, limit, mid)) right = mid-1;
            else left = mid+1;
        }
        
        return answer;
    }
    
    boolean isValid(int[] diffs, int[] times, long limit, int level) {
        long time = 0L; //소요 시간

        //모든 퍼즐 풀이
        for(int i=0; i<n; i++) {
            if(level >= diffs[i]) time += times[i];
            else {
                time += ((diffs[i]-level) * (times[i]+times[i-1]));
                time += times[i];
            }
        }

        //다 풀었을 경우 확인 후 업데이트
        if(time <= limit) {
            answer = Math.min(answer, level);
            return true;
        }
        
        return false;
    }
}