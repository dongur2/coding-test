import java.util.Queue;
import java.util.PriorityQueue;

//필요한 최소 객실 수 
class Solution {
    public int solution(String[][] book_time) {
        //퇴실 -> 10분 청소 -> 입실
        //hh:mm -> 분으로 통일
        
        //대기열(입실 빠른 순)
        Queue<int[]> q = new PriorityQueue<>((a,b) -> {
            return a[0] - b[0];
        });
            
        for(String[] book:book_time) {
            int[] arr = new int[2]; //분으로 변환한 [입실, 퇴실]
            for(int i=0; i<2; i++) {
                String[] t = book[i].split(":"); //시, 분
                int minutes = (60 * Integer.parseInt(t[0])) + Integer.parseInt(t[1]); //분으로 변환
                
                //퇴실일 경우 청소까지 포함해 10분 추가
                if(i==1) minutes += 10;
                arr[i] = minutes;
            }
            q.offer(arr);
        }
        
        int answer = 0;

        //사용중인 방 - 퇴실 시간 빠른 순서대로 정렬
        Queue<Integer> rooms = new PriorityQueue<>((a,b) -> {
            return a - b;
        });
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int start = curr[0]; //현재 방의 입실 시간 
            int end = curr[1]; //현재 방의 퇴실 + 청소 시간
            
            //사용중인 방이 없으면 추가 
            if(rooms.isEmpty()) answer++;
            
            //사용중인 방이 있으면 
            else {
                //퇴실 시간이 가장 빠른 방의 퇴실 시간과 지금 들고있는 방의 입실 시간 비교
                //퇴실 시간보다 입실 시간이 빠르면 새로 추가
                if(start < rooms.peek()) answer++;
                    
                //퇴실 시간과 같거나 퇴실 시간보다 느리게 입실하면 
                else rooms.poll(); //기존 방은 빼고
            }
            rooms.offer(end); //지금 방을 추가
        }
        
        return answer;
    }
}