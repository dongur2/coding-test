import java.util.Queue;
import java.util.PriorityQueue;

//모든 요청 작업 평균 반환 시간의 정수 리턴 
class Solution {
    public int solution(int[][] jobs) {
        int cnt = jobs.length; //모든 작업 개수
        
        //작업을 요청시간 빠른 순서대로 정렬
        Queue<Task> listQ = new PriorityQueue<>((a,b) -> {
            if(a.ask != b.ask) return a.ask - b.ask;
            return a.need - b.need;
        });
        
        //작업 모두 요청시간 큐에 추가 
        for(int i=0; i<cnt; i++) {
            listQ.offer(new Task(i, jobs[i][0], jobs[i][1])); //job[i] = {s, l} (s: i번 작업의 요청시점, l: i번 작업의 소요시간)
        }
        
        //디스크 컨트롤러 큐 - 실제 작업 수행 대기 큐
        Queue<Task> pq = new PriorityQueue<>((a,b) -> {
            if(a.need != b.need) return a.need - b.need; //짧은 소요시간
            if(a.ask != b.ask) return a.ask - b.ask; //빠른 요청 시각
            return a.idx - b.idx; //작은 번호
        });
        
        
        //시간 업데이트하면서 소요시간 더하기
        int sum = 0;
        
        int now = 0;
        
        //모든 작업을 실제 대기 큐에 추가
        while(!listQ.isEmpty()) {
            //[대기 큐가 비어있고 + 남은 작업이 있을 경우] 가장 요청 시각이 빠른 작업 추가
            if(pq.isEmpty() && !listQ.isEmpty()) {
                Task task = listQ.poll();
                pq.offer(task);
            }
            
            //[대기 큐가 비어있지 않을 경우]
            while(!pq.isEmpty()) {
                Task curr = pq.poll(); //가장 우선순위 높은 작업 
                if(now < curr.ask) now = curr.ask; //이전 작업 종료 시점보다 현재 작업 요청 시각이 늦을 경우(대기가 필요할 경우)작업 요청 시각으로 리셋팅
                int end = now + curr.need; //현재 작업 종료 시각
                
                //총 소요시간 더하기
                sum += end - curr.ask;
                
                //현재 시각을 종료 시점으로 리셋팅
                now = end;
                
                //현재 작업 시작~종료시간까지 요청이 들어와 대기하고있는 작업을 추가
                while(!listQ.isEmpty() && listQ.peek().ask <= now) pq.offer(listQ.poll());      
            }
        }
        
        //평균 시간의 정수 리턴
        return (int)((double)sum/cnt);
    }
    
    private class Task {
        int idx, ask, need;
        
        public Task(int idx, int ask, int need) {
            this.idx=idx; this.ask=ask; this.need=need;
        }
    }
}