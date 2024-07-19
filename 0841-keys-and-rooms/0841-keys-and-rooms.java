import java.util.*;

class Solution {
    /*
    방 n개는 0번 방 빼고 다 잠금
    모든 방을 방문하는 것이 목표지만 키가 없으면 들어갈 수 없음
    방에서 숫자에 해당하는 방의 키를 발견 가능 -> 방 잠금 해제 가능

    문제: 모든 방을 방문할 수 있으면 true, 아니라면 false 리턴
    방은 최소 2개, 최대 10^3개

    -> BFS: 인접 노드부터 탐색
     */
    Map<Integer, Boolean> visited; // 방문한 방 인덱스 저장
    Queue<Integer> q; // 대기열

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        visited = new HashMap<>();    
        q = new LinkedList<>();  
           
        //1. 탐색하여 연결된 노드에 방문
        visitRooms(rooms, 0);

        //2. return: 방문 횟수가 방 개수랑 같으면 모든 방 방문 완료, 아니라면 실패
        return visited.keySet().size() == rooms.size();
    }

    private void visitRooms(List<List<Integer>> rooms, int idx) {
        q.offer(idx); //1. 대기열에 현재 노드를 추가
        visited.put(idx, true); //2. 현재 노드 방문 체크

        //3. 대기열이 빌 때까지 반복
        while(!q.isEmpty()) {  
            int nowRoom = q.poll(); //4. 현재 노드
            for(int nextRoom:rooms.get(nowRoom)) { //5. 현재 노드와 연결된 모든 노드를 차례로 방문
                //6. 연결된 노드 중 해당 노드를 방문한 적이 없으면 대기열에 추가하고(다음 턴에 해당 노드와 연결된 노드를 또 탐색하여 방문해야 함) 방문 체크
                if(!visited.containsKey(nextRoom)) { 
                    q.offer(nextRoom); 
                    visited.put(nextRoom, true);
                }
            }
        }
    }
}