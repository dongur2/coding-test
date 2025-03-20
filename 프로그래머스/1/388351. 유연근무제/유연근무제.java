import java.util.*;

/*
상품을 받을 직원 수
- 일주일 동안 각자 설정한 출근 시각에 늦지 않고 출근
- 설정 출근 시각 + 10분까지 출근
- 토요일, 일요일 상관없음
- 시각: 10시13분 => 1013 (10 * 100 + 13)
*/
class Solution {
    
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int people = schedules.length; //직원 수
        int answer = people; //최대 수
        
        int startIdx = startday - 1; //배열 인덱스와 동기화
        
        //각 직원이 요일마다 지각했는지 확인 - 1번이라도 지각하면 무시
        for(int p=0; p<people; p++) {
            //일주일 타임로그 확인
            int today = startday - 1;
            
            int endTime = convertToMinute(schedules[p]) + 10;
            int[] times = timelogs[p];
            
            for(int time : times) {
                //토일 무시
                int minuteTime = convertToMinute(time);
                if(today < 5 && endTime < minuteTime) {
                    answer--;
                    break;
                }
                today = (today + 1) % 7;
            } 
        }
        
        return answer;
    }
    
    private int convertToMinute(int time) {
        int hour = time / 100;
        int min = time % 100;
        
        return hour * 60 + min;
    }
}