import java.util.*;

/*
- 과제 끝낸 순서대로 배열에 담아 리턴!
- 정해둔 시간대로 과제 시작
- 하다 말았으면 새로운거 시작 (최우선)- 다 끝내고나서 멈춰둔 과제 시작 (멈춰둔 것중엔 최근 것부터)
*/
class Solution {
    public String[] solution(String[][] plans) {
        List<String> list = new ArrayList<>();
        
        //과제 수행 큐 - 빠른 시작 시간 순으로 정렬
        PriorityQueue<String[]> q = new PriorityQueue<>(new Comparator<String[]>(){
            @Override
            public int compare(String[] p1, String[] p2) {
                return Integer.parseInt(p1[1]) - Integer.parseInt(p2[1]);
            }
        });
        
        //남은 과제 수행 스택
        Deque<String[]> stack = new ArrayDeque<>();
        
        //큐에 과제 저장
        for(String[] plan : plans) {
            //시작 시간을 분으로 변환하여 저장
            String[] times = plan[1].split(":");
            int hour = Integer.parseInt(times[0]) * 60;
            int min = Integer.parseInt(times[1]);
            plan[1] = String.valueOf(hour + min);
            
            q.offer(plan);
        }
        
        while(!q.isEmpty()) {
           String[] cur = q.poll();
           String subject = cur[0];
           int nowTime = Integer.parseInt(cur[1]);
           int remains = Integer.parseInt(cur[2]);

           //현재 작업 완료시 시각
           int finishTime = nowTime + remains;

           //다음 작업이 있을 경우
           if(!q.isEmpty()) {
               int nxtStart = Integer.parseInt(q.peek()[1]);

               //다음 작업 시각 시간이 현재 작업 완료 시각보다 빠를 경우 
               if(finishTime > nxtStart) {
                   //남은 과제를 스택에 추가
                   stack.push(new String[] {subject, finishTime - nxtStart + ""});

               //다음 작업보다 빠르게/동시에 작업 완료 가능할 경우
               } else {
                   list.add(subject); //일단 완료 리스트에 추가하고
                   
                   //다음 과제 시작 시각보다 빨리 끝냈는지 확인 후 빠르다면, 남았던 과제를 최우선으로 큐에 추가
                   if(finishTime < nxtStart && !stack.isEmpty()) {
                       String[] r = stack.pop();
                       q.offer(new String[] {r[0], finishTime+"", r[1]});
                   }
               }
           } else list.add(subject);
        }
        
        //남은 과제 순서대로 완료
        while(!stack.isEmpty()) {
            list.add(stack.pop()[0]);
        }
        
        String[] answer = new String[plans.length];
        for(int i=0; i<plans.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}