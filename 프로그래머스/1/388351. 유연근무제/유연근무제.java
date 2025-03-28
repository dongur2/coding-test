import java.util.*;

//일주일동안 지각안한 직원 수
class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = schedules.length;
        
        //직원별로 지각 확인
        for(int i=0; i<schedules.length; i++) {
            int deadline = convertToMinute(schedules[i]) + 10;
            
            for(int idx=0; idx<7; idx++) {
                int timelog = convertToMinute(timelogs[i][idx]);
                
                int day = (startday + idx) % 7;
                if(day != 6 && day != 0 && deadline < timelog) { //토일 제외
                    answer--; break;
                }
            }
        }
        
        return answer;
    }
    
    int convertToMinute(int time) {
        int hour = (time / 100) * 60;
        int min = time % 100;
        return hour + min;
    }
}