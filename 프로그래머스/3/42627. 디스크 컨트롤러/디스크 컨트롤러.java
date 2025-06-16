import java.util.*;

//모든 요청 작업의 '평균' 반환 시간의 '정수' 리턴 
class Solution {
    public int solution(int[][] jobs) {
        int times = 0;
        int cnt = jobs.length;
        
        //빠른 요청시간 순서대로 정렬 //요청시간에 따라 작업 요청
        Queue<Task> list = new PriorityQueue<>((a,b) -> {
            if(a.ask != b.ask) return a.ask - b.ask;
            return a.need - b.need;
        });
        for(int idx=0; idx<jobs.length; idx++) {
            int[] j = jobs[idx];
            list.offer(new Task(j[0], j[1], idx));
        }
        
        //디스크 컨트롤러 대기 큐 (소요시간 > 요청시간 > 번호)
        Queue<Task> q = new PriorityQueue<>((a,b) -> {
            if(a.need != b.need) return a.need - b.need;
            if(a.ask != b.ask) return a.ask - b.ask;
            return a.idx - b.idx;
        });
        
        //현재 시각
        int now = 0;
        
        //모든 작업 수행
        while(!list.isEmpty()) {
            //큐가 비어있을 경우 요청 들어온 작업을 즉시 시작
            if(q.isEmpty()) {
                Task task = list.poll();
                q.offer(task);
                now = task.ask; //현재 시간 업데이트 
            }
            
            //큐가 비어있지 않을 경우 우선순위 가장 높은 작업부터 시작 
            while(!q.isEmpty()) {
                Task curr = q.poll();
                int finish = now + curr.need; //작업 종료 시각
                times += finish - curr.ask; //작업 요청 ~ 종료까지 걸린 시간
                now = finish; //현재 시간 업데이트 
                
                //작업 수행 중 들어온 요청은 큐에 저장 
                while(!list.isEmpty() && now >= list.peek().ask) q.offer(list.poll());
            }        
        }
        
        return (int)((double)times / cnt);
    }
    
    private static class Task {
        int ask, need, idx;
        
        public Task(int ask, int need, int idx) {
            this.ask=ask; this.need=need; this.idx=idx;
        }
    }
}