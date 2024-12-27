import java.util.*;
/*
Q. 필요한 최소 객실 수
- 퇴실 -> 10분 -> 사용
*/
class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        //예약시간 빠른 순서대로 정렬
        Arrays.sort(book_time, (book1, book2) -> book1[0].compareTo(book2[0]));
        
        //퇴실시간 빠른 순서대로 정렬
        PriorityQueue<int[]> table = new PriorityQueue<>((book1, book2) -> book1[1] - book2[1]);
        
        //문자열 => 숫자화
        for(String[] book : book_time) {
            int start = convertToInt(book[0]);
            int end = convertToInt(book[1]) + 10;
            
            //배정된 방이 없을 경우
            if(table.isEmpty()) answer++;
            
            //배정된 방의 종료 시간이 현재 새로 등록된 방의 예약 시간보다 뒤라면 확인 필요
            else {
                if(table.peek()[1] > start) answer++;
                else table.poll();
            }
            
            //방 배정
            table.offer(new int[]{start, end});
        }
        
        return answer;
    }
    
    private int convertToInt(String time) {
        String[] arr = time.split(":");
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }
}