import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        //1. 큐 생성 & 최초 합 연산
        Queue<Integer> q1 = new ArrayDeque<>(); 
        Queue<Integer> q2 = new ArrayDeque<>(); 
        
        int cnt = 0;
        long sum1 = 0, sum2 = 0;
        for(int i=0; i<queue1.length; i++) {
            q1.add(queue1[i]);
            sum1 += queue1[i];
            
            q2.add(queue2[i]);
            sum2 += queue2[i];
        }
        
        //2. 반복최소횟수만큼 반복해서 큐 원소 이동 & 합 연산
        while(cnt < 4 * queue1.length) {
            //3. 큐1의 합이 더 크면 큐1에서 추출 -> 큐2에 삽입 :: 카운트 & 합 연산
            if(sum1 > sum2 && !q1.isEmpty()) {
                int deque = q1.poll();
                q2.add(deque);
                
                sum1 -= deque;
                sum2 += deque;
                
                cnt++;                
            } else if (sum1 < sum2&& !q2.isEmpty()) {
            //4. 큐1의 합이 더 작으면 큐2에서 추출 -> 큐1에 삽입 :: 카운트 & 합 연산
                int deque = q2.poll();
                q1.add(deque);
                
                sum1 += deque;
                sum2 -= deque;
                
                cnt++;
            } else return cnt;
        }        
        //5. 반복문이 끝났는데도 합이 같지 않으면 -1 리턴
        return -1;
    }
}