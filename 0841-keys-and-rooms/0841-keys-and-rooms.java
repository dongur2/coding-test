import java.util.*;

class Solution {
    /*
    ** 모든 방에 대한 방문 가능 여부를 반환
    - 0번 방은 잠기지 않음: 무조건 0번에서 시작(루트 노드)
    - 방 n개에는 다른 방의 열쇠가 있어,방을 방문하고 나면 그 방에 있는 열쇠에 해당하는 방에 방문할 수 있음
    --> 방(노드)과 열쇠에 해당하는 방(노드)들이 연결
    
    모든 방에 대한 순회가 가능한지 여부를 반환해야하므로 BFS, DFS 모두 가능
    */
    
    boolean answer;
    boolean[] visited;
    
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        answer = false; 
        visited = new boolean[rooms.size()];
        
        bfs(rooms);
        
        int cnt = 0;
        for(boolean v:visited) {
            if(v) cnt++;
        }
        
        return cnt == rooms.size();
    }
    
    //레벨을 차례차례 순회
    void bfs(List<List<Integer>> rooms) {
        //1.시작점 설정
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        visited[0] = true;
        
        while(!q.isEmpty()) {
            //2. 대기열에서 노드 방문
            int cur = q.poll();

            //3. 방문한 노드의 인접 노드를 탐색하여 예약 
            for(int nextNode : rooms.get(cur)) {
                if(visited[nextNode]) continue; //이미 방문했던 노드는 무시

                visited[nextNode] = true;
                q.offer(nextNode);
            }
        }
    }
}