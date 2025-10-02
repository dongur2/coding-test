import java.util.Map; import java.util.HashMap;
import java.util.List; import java.util.ArrayList;
import java.util.Deque; import java.util.ArrayDeque;
class Solution {
    public int solution(int n, int[][] computers) {
        //그래프 연결리스트화
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0; i<computers.length; i++) {
            for(int j=0; j<computers.length; j++) {
                if(i==j) continue;
                if(computers[i][j] == 1) map.computeIfAbsent(i, k->new ArrayList<>()).add(j);
            }
        }
        
        //bfs로 한 네트워크에 포함된 모든 장치 방문
        int answer = 0;
        boolean[] visited = new boolean[computers.length];
        for(int i=0; i<computers.length; i++) {
            if(!visited[i]) {
                bfs(map, visited, i);
                answer++;
            }
        }
        
        return answer;
    }
    
    void bfs(Map<Integer, List<Integer>> map, boolean[] visited, int start) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start); visited[start] = true;
        
        while(!q.isEmpty()) {
            int curr = q.poll();
            
            if(!map.containsKey(curr)) continue;
            for(int next : map.get(curr)) {
                if(visited[next]) continue;
                q.offer(next); visited[next] = true;
            }
        }
    }
}