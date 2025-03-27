import java.util.*;

//과제 이름을 끝낸 순서대로 리턴
class Solution {
    public String[] solution(String[][] plans) {
        List<String> list = new ArrayList<>();
        
        //시작시간에 시작하는 과제 큐
        Queue<String[]> q = new PriorityQueue<>(new Comparator<String[]>(){
            @Override
            public int compare(String[] p1, String[] p2) {
                return Integer.parseInt(p1[1]) - Integer.parseInt(p2[1]);
            }
        });
        
        //큐에 과제 저장 - 시작 시간은 분으로 변환
        for(String[] p:plans) {
            String[] time = p[1].split(":");
            int hour = Integer.parseInt(time[0]) * 60;
            int min = Integer.parseInt(time[1]);
            int minuteTime = hour + min;
            
            q.offer(new String[] {p[0], minuteTime+"", p[2]});
        }
        
        //하다가 만 과제 저장할 스택 - 최근에 멈춘 것부터 꺼내야 함
        Deque<String[]> st = new ArrayDeque<>();
        
        //과제 수행
        while(!q.isEmpty()) {
            String[] cur = q.poll();
            String subject = cur[0];
            int nowTime = Integer.parseInt(cur[1]);
            int remains = Integer.parseInt(cur[2]);
            
            //다음 과제가 있을 경우 다음 과제 시작 전에 마치면 완료 후 이전 진행중 과제 확인 및 진행
            if(q.size() > 0) {
                int finish = nowTime + remains;
                
                String[] nxt = q.peek();
                int nxtStart = Integer.parseInt(nxt[1]);
                
                //다음 과제 전에 마침
                if(finish <= nxtStart) {
                    list.add(subject); //완료
                    
                    //시간이 남았을 경우 & [멈춘 과제가 있을 경우] 멈춘 과제 진행
                    if(finish < nxtStart && !st.isEmpty()) {
                        String[] work = st.pop();
                        q.offer(new String[] {work[0], finish+"", work[1]});   
                    }
                }
                
                //다음 과제 시작 시간에도 못 마쳤다면 스택에 추가
                else {
                    int remain = finish - nxtStart;
                    st.push(new String[] {subject, remain+""});
                }
            }
            
            //다음 과제가 없을 경우 완료
            else list.add(subject);
        }
        
        //못마친 과제는 저장 후 최근에 멈춘 것부터 진행
        while(!st.isEmpty()) {
            String[] work = st.pop();
            list.add(work[0]);
        }
        
        String[] answer = new String[list.size()];
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}