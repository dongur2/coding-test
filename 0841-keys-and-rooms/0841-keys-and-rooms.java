import java.util.*;

class Solution {
    /*
    Q. 모든 방을 방문할 수 있는지 여부를 리턴 (방문할 수 있으면 true)
    - 0번 ~ n-1번 방
    - 0번 방을 제외하고 잠금
    - 각 방에는 다른 방의 열쇠가 있으므로, 그 방을 방문한 뒤에는 해당 방을 들어갈 수 있음
    
    - 방에 있는 열쇠 -> 노드와 연결된 인접노드로 처리
    */
    
    Map<Integer, Boolean> visited = new HashMap<>();
    
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        dfs(rooms, 0);
        return visited.keySet().size() == rooms.size();
    }
    
    void dfs(List<List<Integer>> rooms, int node) {
        //현재 노드 방문
        visited.put(node, true);
        
        //인접 노드 방문
        if(!rooms.get(node).isEmpty()) {
            for(Integer nxt : rooms.get(node)) {
                if(!visited.containsKey(nxt)) dfs(rooms, nxt);
            }
        }
    }
}