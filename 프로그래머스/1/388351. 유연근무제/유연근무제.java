/*
    상품을 받을 직원의 수?
    - 오늘부터 일주일 동안 각자 설정한 출근 희망 시각에 늦지 않으면 상품
    - 출근 희망 시각 + 10분까지 출근
    - 토요일, 일요일은 영향 없음
    - 시간 = 시*100 + 분
*/
class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int n = schedules.length; //직원 수
        
        int answer = n;
        
        int[] deadline = new int[n]; //희망 시각 분으로 변환
        for(int i=0; i<n; i++) {
            deadline[i] = convertToMin(schedules[i]) + 10;
        }
        
        //startday - 1-5:월-금, 6,7:토일
        
        //일주일 간 지각 확인 
        for(int i=0; i<n; i++) {
            int today = startday;
            int cut = deadline[i];
            
            for(int t : timelogs[i]) {
                //주말은 건너뛰기
                if(today++ >= 6) {
                    if(today == 8) today = 1;
                    continue;
                }
                
                int min = convertToMin(t);
                if(min > cut) {
                    answer--; break;
                }
            }
        }
        
        return answer;
    }
    
    private int convertToMin(int time) {
        int hour = time / 100;
        int min = time % 100;
        return (hour * 60) + min;
    }
}