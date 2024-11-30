import java.util.*;

class Solution {
    /*
    Q. 주어진 그래프가 2그룹으로 나누어지는지 여부 리턴
    - 무방향 그래프
    - n개 노드: 0번 ~ n-1번
    */
    
    Map<Integer, Integer> map;
    boolean[] visited;
    
    boolean answer = true;
    public boolean isBipartite(int[][] graph) {
        map = new HashMap<>();
        visited = new boolean[graph.length];
        
        for(int i=0; i<graph.length; i++) {
            if(visited[i]) continue;
            if(!dfs(graph, i, 0)) return false;
        }
        
        return answer;
    }

    boolean dfs(int[][] graph, int node, int set) {
        visited[node] = true;
        
        if(graph[node].length > 0) {
            map.put(node, set);
            
            for(int nxt : graph[node]) {
                if(visited[nxt]) {
                    if(map.get(nxt) == map.get(node)) return false;
                } else {
                    if (!dfs(graph, nxt, 1-set)) return false;
                }
            }
        }
        
        return true;
    }
}