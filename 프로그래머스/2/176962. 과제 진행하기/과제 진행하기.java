import java.util.*;

/*
과제 이름을 종료 순서대로 리턴
- 시작 시간에 시작
- 다음 과제 시작 시간에도 마치지 못했을 경우 다음 과제 시작
- 다음 과제 시작 시간 전에 마쳤을 경우, 이전에 멈춰둔 과제 시작
- 멈춘 과제가 여러 개일 경우 최근 것부터 시작
*/
class Solution {
    List<String> answer = new ArrayList<>();
    
    public String[] solution(String[][] plans) {
        //기존 과제 시작 시간에 따른 수행 큐: 시작 시간이 빠를 수록 우선
        PriorityQueue<String[]> q = new PriorityQueue<>(new Comparator<String[]>(){
            @Override
            public int compare(String[] p1, String[] p2) {
                return Integer.parseInt(p1[1]) - Integer.parseInt(p2[1]);
            }
        });
        
        //시작시간을 분으로 전환한 뒤에 큐에 추가
        for(String[] plan:plans) {
            plan[1] = convertMinutes(plan);
            q.offer(plan);
        }
        
        //남은 과제 스택: 최근 것부터 수행
        Deque<String[]> st = new ArrayDeque<>();
        
        while(!q.isEmpty()) {
            String[] cur = q.poll();
            String subject = cur[0];
            int time = Integer.parseInt(cur[1]);
            int required = Integer.parseInt(cur[2]);
            
            int finishTime = time + required; //과제를 모두 완료했을 때 시각
            
            //다음 과제가 있을 경우
            if(!q.isEmpty()) {
                String[] nxt = q.peek();
                int nxtTime = Integer.parseInt(nxt[1]);
                
                //다음 과제 시작 전에 완료
                if(finishTime <= nxtTime) {
                    answer.add(subject);
                    
                    //미종료 과제가 있을 경우 그것부터
                    if(finishTime < nxtTime && !st.isEmpty()) {
                        String[] remainTask = st.pop();
                        q.offer(new String[] {remainTask[0], time + required + "", remainTask[1]});
                    }
                }
                
                //다음 과제 시작 전 미완료
                else {
                    int remains = finishTime - nxtTime; //미완료분
                    st.push(new String[] {subject, remains+""}); //스택에 추가
                }
            
            //다음 과제 없을 경우 완료
            } else answer.add(subject);
        }
        
        //남은 과제 완료
        while(!st.isEmpty()) {
            answer.add(st.pop()[0]);
        }
        
        String[] arr = new String[answer.size()];
        for(int i=0; i<answer.size(); i++) {
            arr[i] = answer.get(i);
        }
        return arr;
    }
    
    private String convertMinutes(String[] plan) {
        String[] time = plan[1].split(":");
        int hour = Integer.parseInt(time[0]) * 60;
        int min = Integer.parseInt(time[1]);
        
        return String.valueOf(hour+min);
    }
}