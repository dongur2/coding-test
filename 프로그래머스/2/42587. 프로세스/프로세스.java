import java.util.*;

class Solution {
    /*
    * 운영체제가 규칙에 따라 프로세스를 관리할 때, "특정 프로세스"가 "실행되는 차례"를 리턴
    * 1. 대기 큐에서 프로세스를 꺼내는데,
    * 2. 대기 큐에 우선순위가 더 높은 프로세스가 있다면 꺼낸 프로세스를 다시 넣음
    * 3. 없다면 그대로 실행 -> 프로세스 종료
    => 우선순위가 더 높은 프로세스가 있으면, 그 프로세스 차례가 올 때까지 대기 큐는 앞의 값이 뒤로 가는 회전 반복
    
    priorities: 대기 큐에 있는 프로세스의 우선순위가 담긴 배열
    - 1 <= priorities.length <= 10^2
    - 1 <= priorities[i] <= 9
    - priorities의 원소가 우선순위: 클수록 높은 우선순위
    
    location: 몇 번째로 실행되는지 알고 싶은 프로세스의 위치
    - 0 <= location <= (대기 큐에 있는 프로세스 수 - 1)
    - location이 priorities에 있는 해당 프로세스의 인덱스
    */

    public int solution(int[] priorities, int location) {
        //1. 대기 큐 생성: *우선순위 큐* - 우선순위가 높은 요소가 항상 앞으로 위치
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        
        //2. priorities의 중요도를 대기 큐에 저장
        for(int priority:priorities) {
            q.add(priority);
        }
        
        //3. 대기 큐가 빌 때까지 순회: 모든 프로세스 완료
        int cnt = 0;
        while(!q.isEmpty()) {
            //4. 원래 순서대로 계속해서 순회
            for(int i=0; i<priorities.length; i++) {
                if(q.peek() == priorities[i]) {
                    q.poll();
                    cnt++;
                    
                    //5. location에 해당하는 위치의 프로세스라면 즉시 리턴
                    if(i == location) return cnt;
                }
            }
        }
        return cnt;
    }
}