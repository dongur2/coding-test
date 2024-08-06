import java.util.*;

class Solution {
    /*
    문제: 각 가격마다 해당 가격이 떨어지지 않은 초를 기록하여 반환
    */
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        //과거 가격을 저장할 스택
        Deque<Integer> past = new ArrayDeque<>();
        
        //각 초를 차례대로 순회
        for(int sec=0; sec<prices.length; sec++) {
            //현재 가격이 과거 가격과 같거나 낮아질 때까지 스택에서 과거 가격을 삭제: 삭제하는 과거 가격은 정답 배열에 가격이 떨어지지 않았던 기간을 기록
            while(!past.isEmpty() && prices[sec] < prices[past.peek()]) {
                int pastSec = past.pop();
                answer[pastSec] = sec - pastSec;
            }
            past.push(sec);
        }
        
        //과거 가격이 반복이 끝날 때까지 떨어지지 않으면 스택에 시간대가 남아있으므로 처리
        while(!past.isEmpty()) {
            int pastSec = past.pop();
            answer[pastSec] = prices.length-1 - pastSec;
        }
        
        return answer;
    }
    
}