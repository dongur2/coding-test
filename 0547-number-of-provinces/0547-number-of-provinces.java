/*
    도시 n개 - 몇 개는 연결되어 있고, 어떤 건 아님
    province: 연결 성분
    province 개수 리턴
 */
import java.util.List; import java.util.ArrayList;
import java.util.Map; import java.util.HashMap;
class Solution {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    boolean[] visited;

    public int findCircleNum(int[][] isConnected) {
        makeGraph(isConnected);

        visited = new boolean[isConnected.length];

        int provinces = 0;

        for(int curr=0; curr<isConnected.length; curr++) {
            if(!visited[curr]) dfs(curr, provinces++);
        }

        return provinces;
    }

    void makeGraph(int[][] isConnected) {
        for(int city=0; city<isConnected.length; city++) {
            for(int other=city+1; other<isConnected.length; other++) {
                if(isConnected[city][other] == 0) continue;

                graph.computeIfAbsent(city, k -> new ArrayList<>()).add(other);
                graph.computeIfAbsent(other, k -> new ArrayList<>()).add(city);
            }
        }
    }

    void dfs(int curr, int p) {
        visited[curr] = true;

        if(graph.get(curr) != null) {
            for(int nxt : graph.get(curr)) {
                if(!visited[nxt]) dfs(nxt, p);
            }
        }
    }
}