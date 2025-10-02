import java.util.List; import java.util.ArrayList;
import java.util.Deque; import java.util.ArrayDeque;

class Solution {
    int cnt = 0;
    boolean[] checked;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // int cnt = bfs(rooms);    
        // return cnt == rooms.size();

        checked = new boolean[rooms.size()];
        dfs(rooms, 0);
        return cnt == rooms.size();
    }

    void dfs(List<List<Integer>> rooms, int curr) {
        //방문 체크 
        checked[curr] = true; cnt++;

        if(rooms.get(curr) != null) {
            for(int key : rooms.get(curr)) {
                if(!checked[key]) dfs(rooms, key);
            }
        }
    }

    int bfs(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        int cnt = 0; //방문한 방 개수 

        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0); visited[0] = true;

        while(!q.isEmpty()) {
            int curr = q.poll();
            cnt++; //방문 카운트 

            if(rooms.get(curr) == null) continue; //방에 열쇠 없으면 넘어감
            for(int key : rooms.get(curr)) {
                if(visited[key]) continue; //이미 방문한 적 있는 방이면 무시 
                q.offer(key); visited[key] = true;
            }
        }

        return cnt;
    }
}