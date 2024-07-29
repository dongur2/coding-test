import java.util.*;

class Solution {
    /*
    필요한 최소 객실 수를 리턴
    */
    public int solution(String[][] book_time) {
        //대실 시작이 빠른 순서대로 정렬
        Arrays.sort(book_time, (book1, book2) -> book1[0].compareTo(book2[0]));
        
        //예약 대기열(큐)을 대실 종료가 빠른 순서대로 정렬
        PriorityQueue<int[]> booking = new PriorityQueue<>((book1, book2) -> book1[1] - book2[1]);
        
        //배열을 앞에서부터(대실 시작이 빠른 순서) 순회하며,
        int rooms = 0;
        for(String[] book:book_time) {
            //문자열로 주어진 시간 -> 정수화
            String[] splited = book[0].split(":");
            int start = Integer.parseInt(splited[0]) * 60 + Integer.parseInt(splited[1]);
            splited = book[1].split(":");
            int end = Integer.parseInt(splited[0]) * 60 + Integer.parseInt(splited[1]) + 10;
            
            //대기열이 비었거나, 대기열의 가장 처음 예약건의 종료 시간보다 현재 대실 시작건의 시간이 빠르면: 처음 예약건과 새로운 예약건을 큐에 넣고 객실 추가 카운트
            if(booking.isEmpty()) {
                rooms++;
                booking.offer(new int[]{start, end});
            } else {
                int[] fastest = booking.poll();
                
                //현재 대실 시작 시간이 대기열 처음 예약건의 종료보다 뒤라면 추가 카운트 없이 현재 대실 예약건을 대기열에 저장
                if(start >= fastest[1]) booking.offer(new int[]{start, end});
                else { 
                    rooms++;
                    booking.offer(fastest);
                    booking.offer(new int[]{start, end});
                }
            }
        }
        return rooms;
    }
}