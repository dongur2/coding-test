import java.util.*;

class Solution {
    /*
    Q. 작업 요청 ~ 종료 시간의 평균을 최소로 줄였을 때의 시간을 반환
    - 각 작업의 (대기 시간 + 소요 시간) 합 / 처리 작업 개수
    - jobs[i] = [작업 요청 시점, 작업 소요 시간]
    */
    public int solution(int[][] jobs) {
        //작업을 요청이 빠른 순서대로 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        return getMinAverage(jobs);
    }
    
    int getMinAverage(int[][] jobs) {
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); //소요시간 짧은 작업이 우선
        
        int idx = 0; //작업 포인터
        int endTime = 0; //현재 작업 종료 시점
        int finished = 0; //완료된 작업 개수
        
        int sum = 0; //대기 시간 + 소요 시간
        
        //모든 작업을 완료할 때까지 반복
        while(finished < jobs.length) {
            //현재 작업을 진행하는 동안 들어온 요청을 힙에 추가
            while(idx < jobs.length && endTime >= jobs[idx][0]) {
                pq.add(jobs[idx++]);
            } 
            
            //남은 작업이 존재하지만 && 힙이 비었을 때(하드디스크가 작업을 수행하고 있지 않을 때): 다음 작업이 진행될 수 있도록 시점을 다음 작업 요청 시점으로 설정
            if(pq.isEmpty()) endTime = jobs[idx][0];
            
            //이미 요청이 들어온 작업이 있을 경우
            else { //소요시간이 짧은 작업부터 처리
                int[] cur = pq.poll();
                sum += endTime - cur[0] + cur[1]; //분자
                endTime += cur[1]; //작업종료시점 업데이트
                finished++; //완료작업 카운트
            }
        }
        return (int)((long)sum / finished);
    }
}