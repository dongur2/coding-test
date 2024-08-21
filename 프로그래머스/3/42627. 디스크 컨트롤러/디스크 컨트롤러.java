import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        //주어진 jobs배열을 요청 시간이 빠른 순서대로 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        //힙 생성: 짧은 소요 시간을 가진 원소가 우선
        Queue<int[]> heap = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        int cnt = 0; //작업 완료된 개수
        int finished = 0; //현재까지의 작업을 종료한 시점
        int sum = 0; //각 작업의 대기 + 작업 시간의 합 (평균시간의 분자 부분)
        int idx = 0; //jobs배열 인덱스
        
        //모든 작업을 완료할 때까지 반복:
        while(cnt < jobs.length) {
            //하나의 작업이 완료되는 시점까지 들어온 모든 요청을 힙에 예약
            while(idx < jobs.length && jobs[idx][0] <= finished) {
                heap.add(jobs[idx++]);
            }
            
            //힙이 비어있을 경우:
            /*
            "현재까지의 작업을 종료한 시점이 다음 작업의 요청 시간보다 빠른 상태"임을 의미 -> 그래서 아직 다음 작업 요청이 들어오지 않았음
            그러므로 finished를 <다음 작업의 요청 시간>으로 수동으로 업데이트
            이전 idx까지는 이미 힙에 예약되어 작업 완료된 상태이므로 현재 idx는 다음 작업을 가리키게 됨
            */
            if(heap.isEmpty()) finished = jobs[idx][0];
            
            //힙이 비어있지 않으면: 힙에 예약된 작업 중 소요 시간이 짧은 작업부터 수행
            else { 
                int[] cur = heap.poll();
                //이전 작업 완료 시점이 해당 작업은 시작 시점이 되니까, 요청시점 - 이전 작업 종료 시점이 대기 시간
                sum += (finished - cur[0]) + cur[1];
                finished += cur[1]; //작업 종료 시점 업데이트: 현재 작업까지 완료
                cnt++; //완료된 작업 카운트
            }
        }
        
        return (int)((long)sum / jobs.length);
    }
}