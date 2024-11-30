import java.util.*;

class Solution {
    /*
    Q. 주어진 그래프가 2그룹으로 나누어지는지 여부 리턴
    - 무방향 그래프
    - n개 노드: 0번 ~ n-1번
    */
    
    Map<Integer, Integer> map;
    boolean answer = true;
    
    public boolean isBipartite(int[][] graph) {
        map = new HashMap<>();
        
        for(int i=0; i<graph.length; i++) {
            dfs(graph, i, 0);
        }
        
        return answer;
    }

    void dfs(int[][] graph, int node, int set) {
        //이미 방문한 적 있으면 무시
        if(map.get(node) == null) map.put(node, set);
        
        for(int nxt : graph[node]) {
            if(map.get(nxt) != null) { //방문한 적 있는 인접노드
                if(map.get(nxt) == map.get(node)) {
                    answer = false; return;
                }
            //방문한 적 없는 인접노드
            } else dfs(graph, nxt, 1-set);
        }
    }
}