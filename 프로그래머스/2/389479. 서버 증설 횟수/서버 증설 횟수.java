import java.util.Queue; import java.util.ArrayDeque;
//하루 동안 모든 게임 이용자가 게임을 하기 위해 증설해야 하는 최소 서버 증설 횟수
class Solution {
    public int solution(int[] players, int m, int k) {
        //m: 서버 1대에서 수용 가능한 이용자 수 
        //k: 서버 유효 기간
        
        int answer = 0; //서버 증설 횟수 
        
        //서버 생성하면 만든 시각으로 큐에 저장: 현재 시간 - 서버 생성 시간 == k이면 소멸
        Queue<Integer> q = new ArrayDeque<>();
        
        for(int t=0; t<24; t++) {
            while(!q.isEmpty() && t - q.peek() == k) q.poll(); //유효 기간 지난 서버는 소멸 
            
            int p = players[t]; //현재 접속자 수
            int capacity = m + q.size() * m; //현재 유효한 서버 수용자 수 (디폴트 m에 서버 추가당 m명씩 추가)
            
            //유효 수용자 수보다 접속자 수가 적으면 지나감 
            //유효 수용자 수와 접속자 수가 같거나 접속자가 많으면 서버 증설
            while(p >= capacity) {
                q.offer(t); //현재 시간 시작 
                capacity += m; //수용자 수 업데이트 
                answer++;
            }
        }
        
        return answer;
    }
}