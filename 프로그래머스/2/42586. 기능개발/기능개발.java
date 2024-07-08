import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        //1. 작업순서줄 큐를 생성하고 작업배열의 "인덱스"를 넣어서 초기화
        //   작업량 변경할 배열 복사, 정답으로 리턴할 리스트 생성
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=0; i<progresses.length; i++) {
            q.add(i);
        }

        int[] table = Arrays.copyOf(progresses, progresses.length);
        List<Integer> res = new ArrayList<>();
        
        //2. 작업줄(큐)이 빌 때까지 작업 진행
        while(!q.isEmpty()) {
            //3. 각 작업에 해당하는 작업속도(작업량) 합산
            for(int i=0; i<table.length; i++) {
                table[i] += speeds[i];
            }
            
            //4. 큐 맨 앞 작업이 작업진도가 100을 만족하는 동안 계속해서 값 꺼내기 - 꺼낼 때마다 카운트
            int cnt = 0;
            while(!q.isEmpty() && table[q.peek()] >= 100) {
                q.poll();
                cnt++;
            }
            //5. 다 꺼냈으면 최종 카운트를 정답 리스트에 추가: 그 날 배포한 기능 최종 개수
            //- cnt가 0일 경우: 그 날 배포한 기능이 없으므로 넘어감
            if(cnt > 0) res.add(cnt);
        }
           
        //6. 리스트를 배열로 변환해 리턴
        return res.stream().mapToInt(r->r).toArray();
    }
}