import java.util.*;

/*
Q. 주어진 그래프의 노드가 2그룹으로 나눠지는지 여부 리턴
- 0 ~ n-1번 노드
- graph[u]는 u번 노드의 인접 노드 목록

- 규칙: 인접한 노드끼리는 다른 그룹에 소속되어야 한다.

- 모든 노드를 순회하며 확인해야하므로 dfs 활용
*/
class Solution {
    Map<Integer, Integer> map;
    
    public boolean isBipartite(int[][] graph) {
        map = new HashMap<>();
        for(int i=0; i<graph.length; i++) {
            if(map.containsKey(i)) continue;
            
            if(!setGroupAndCheckIsBipartite(graph, i, 0)) return false;
        }
        return true;
    }
    
    boolean setGroupAndCheckIsBipartite(int[][] graph, int node, int group) {
        //방문한 적 있는 노드라면 그룹 확인
        if(map.containsKey(node)) {
            if(group != map.get(node)) return false;
            return true;
        } else {
            //방문한 적 없는 노드라면 그룹 부여
            map.put(node, group);
            for(int nxt : graph[node]) {
                if(!setGroupAndCheckIsBipartite(graph, nxt, 1-group)) return false;
            }       
        }
        return true;
    }
        
}