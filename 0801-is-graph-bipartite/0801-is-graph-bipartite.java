import java.util.Map; import java.util.HashMap;
import java.util.Deque; import java.util.ArrayDeque;

class Solution {
    Map<Integer, Character> group = new HashMap<>(); //노드 그룹 부여  A/B
    
    public boolean isBipartite(int[][] graph) {
        for(int i=0; i<graph.length; i++) {
            // if(!group.containsKey(i) && !bfs(graph, i)) return false; 
            if(!group.containsKey(i) && !dfs(graph, i, 'A')) return false; 
        }
        return true;
    }

    boolean dfs(int[][] graph, int curr, char g) {
        group.put(curr, g);

        if(graph[curr].length == 0) return true;
        for(int next : graph[curr]) {
            //이미 그룹 부여되어있으면 다른지 확인
            if(group.containsKey(next)) {
                if(group.get(next) == g) return false;
            }
            //그룹 부여X -> 부여
            else {
                if(!dfs(graph, next, (g == 'A') ? 'B' : 'A')) return false;
            }
            
        }

        return true;
    }

    boolean bfs(int[][] graph, int start) {
        group.put(start, 'A'); //출발점

        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);

        while(!q.isEmpty()) {
            int curr = q.poll();
            char currGroup = group.get(curr);

            if(graph[curr].length == 0) continue;
            for(int next : graph[curr]) {
                //이미 그룹 부여되어있으면 다른지 확인
                if(group.containsKey(next)) {
                    if(group.get(next) == currGroup) return false; //그룹 같으면 실패 
                }
                //그룹 부여X -> 부여
                else {
                    group.put(next, (currGroup == 'A') ? 'B' : 'A');
                    q.offer(next);
                }
            }
        }

        return true;
    }
}